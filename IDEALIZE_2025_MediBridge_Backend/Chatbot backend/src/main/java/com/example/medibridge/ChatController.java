package com.example.medibridge;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatController {

    @PostMapping("/reply")
    public ResponseEntity<String> getReply(@RequestBody Map<String, String> request) {
        String userInput = request.get("message").toLowerCase();
        String reply;

        // Main menu responses
        switch (userInput) {
            // Initial greeting (root menu)
            case "init":
                reply = "Hi, I'm your digital assistant. Please select what you need to know about:\n"
                        + "- Donations\n"
                        + "- Volunteer\n"
                        + "- Hospitals\n"
                        + "- Other";
                break;

            // Donations sub-menu
            case "donations":
                reply = "Select a donation type:\n"
                        + "- Blood\n"
                        + "- Organs\n"
                        + "- Goods\n"
                        + "- Money";
                break;

            // Donation type instructions
            case "blood":
                reply = "To donate blood:\n"
                        + "1. Go to dashboard\n"
                        + "2. Select donation type: Blood\n"
                        + "3. Fill out the form\n"
                        + "4. Wait for confirmation.\n\n"
                        + "Any more questions? (Yes/No)";
                break;
            case "organs":
                reply = "To donate organs:\n"
                        + "1. Go to dashboard\n"
                        + "2. Select donation type: Organ\n"
                        + "3. Fill out the form\n"
                        + "4. Wait for confirmation.\n\n"
                        + "Any more questions? (Yes/No)";
                break;
            case "money":
                reply = "To donate money:\n"
                        + "1. Go to dashboard\n"
                        + "2. Select donation type: Money\n"
                        + "3. Fill out the form\n"
                        + "4. Wait for confirmation.\n\n"
                        + "Any more questions? (Yes/No)";
                break;
            case "goods":
                reply = "To donate goods:\n"
                        + "1. Go to dashboard\n"
                        + "2. Select donation type: Goods\n"
                        + "3. Fill out the form\n"
                        + "4. Wait for confirmation.\n\n"
                        + "Any more questions? (Yes/No)";
                break;

            // Other options
            case "volunteer":
                reply = "To volunteer:\n"
                        + "1. Go to dashboard\n"
                        + "2. Click the volunteer card\n"
                        + "3. Fill out the form\n"
                        + "4. You'll be contacted soon.\n\n"
                        + "Any more questions? (Yes/No)";
                break;
            case "hospitals":
                reply = "Hospital details are available in the hospital list section of your dashboard.\n\n"
                        + "Any more questions? (Yes/No)";
                break;
            case "other":
                reply = "Please refer to our FAQ page and send us your feedback!\n\n"
                        + "Any more questions? (Yes/No)";
                break;

            // Conversation flow control
            case "yes":
                reply = "What do you need to know about:\n"
                        + "- Donations\n"
                        + "- Volunteer\n"
                        + "- Hospitals\n"
                        + "- Other";
                break;
            case "no":
                reply = "Alright! Have a nice day.";
                break;

            // Default response
            default:
                reply = "I didn't understand that. Please select from the options below:\n"
                        + "- Donations\n"
                        + "- Volunteer\n"
                        + "- Hospitals\n"
                        + "- Other";
        }

        return ResponseEntity.ok(reply);
    }
}