package com.Elites.ResolveHub.controller;

import com.Elites.ResolveHub.model.Complaint;
import com.Elites.ResolveHub.model.User;
import com.Elites.ResolveHub.service.ComplaintService;
import com.Elites.ResolveHub.service.EmailService;
import com.Elites.ResolveHub.service.NotificationService;
import com.Elites.ResolveHub.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
@CrossOrigin("*")
public class ComplaintController {

    private final ComplaintService complaintService;
    private final UserService userService;
    private final NotificationService notificationService;
    private final EmailService emailService;

    public ComplaintController(ComplaintService complaintService,
            UserService userService,
            NotificationService notificationService,
            EmailService emailService) {
this.complaintService = complaintService;
this.userService = userService;
this.notificationService = notificationService;
this.emailService = emailService;
}
    

    // CREATE
    @PostMapping("/create")
    public Complaint createComplaint(@RequestBody Complaint complaint,
                                     @RequestParam String email) {

        User user = userService.findByEmail(email);

        complaint.setUserId(user.getId());
        complaint.setStatus("Pending");

        Complaint saved = complaintService.createComplaint(complaint);

        // 🔔 notify admin
        List<User> users = userService.getAllUsers();

        for (User u : users) {
            if (u.getRole().equalsIgnoreCase("ADMIN")) {
                notificationService.createNotification(
                        u.getId(),
                        "New complaint: " + complaint.getTitle()
                );
            }
        }

        return saved;
    }

    // USER COMPLAINTS
    @GetMapping("/my")
    public List<Complaint> myComplaints(@RequestParam String email) {

        User user = userService.findByEmail(email);
        return complaintService.getUserComplaints(user.getId());
    }

    // ADMIN
    @GetMapping("/all")
    public List<Complaint> allComplaints(@RequestParam String email) {

        User user = userService.findByEmail(email);

        if (!user.getRole().equalsIgnoreCase("ADMIN")) {
            throw new RuntimeException("Access Denied: Admin only");
        }

        return complaintService.getAllComplaints();
    }
    // UPDATE
    @PutMapping("/{id}/status")
    public Complaint updateStatus(@PathVariable Long id,
                                 @RequestParam String status,
                                 @RequestParam String email) {

        User admin = userService.findByEmail(email);

        if (!admin.getRole().equalsIgnoreCase("ADMIN")) {
            throw new RuntimeException("Admin only");
        }

        Complaint updated = complaintService.updateStatus(id, status);
     // ✅ GET USER WHO CREATED COMPLAINT
        User complaintUser = userService.getAllUsers()
                .stream()
                .filter(u -> u.getId().equals(updated.getUserId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found"));
     // ✅ 🔥 SEND EMAIL HERE
        emailService.sendEmail(
                complaintUser.getEmail(),
                "Complaint Status Updated",
                "Hello,\n\nYour complaint status is now: " + status + "\n\nThank you."
        );

        // 🔔 notify user
        notificationService.createNotification(
                updated.getUserId(),
                "Your complaint updated to: " + status
        );

        return updated;
    }
 // DELETE complaint (ADMIN ONLY)
    @DeleteMapping("/{id}")
    public String deleteComplaint(@PathVariable Long id,
                                 @RequestParam String email) {

        User admin = userService.findByEmail(email);

        if (!admin.getRole().equalsIgnoreCase("ADMIN")) {
            throw new RuntimeException("Admin only");
        }

        complaintService.deleteComplaint(id);

        return "Complaint deleted successfully";
    }
}