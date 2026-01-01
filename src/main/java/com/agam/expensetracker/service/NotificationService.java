package com.agam.expensetracker.service;

import com.agam.expensetracker.dto.ExpenseDTO;
import com.agam.expensetracker.entity.ProfileEntity;
import com.agam.expensetracker.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final ProfileRepository profileRepository;
    private final EmailService emailService;
    private final ExpenseService expenseService;


    @Value("${money.manager.frontend.url}")
    private String frontEndUrl;


    @Scheduled(cron = "0 0 22 * * *",zone = "IST")
    public void sendDailyIncomeExpenseReminder() {
        log.info("Job Started : sendDailyIncomeExpenseReminder");
        List<ProfileEntity> profiles = profileRepository.findAll();

        for(ProfileEntity profile : profiles){
            String body =
                    "Hi " + profile.getFullName() + ",<br><br>" +
                            "This is a friendly reminder to add your income and expenses for today in Money Manager.<br><br>" +
                            "<a href=\"" + frontEndUrl + "\" " +
                            "style=\"display:inline-block;padding:10px 20px;background-color:#4CAF50;" +
                            "color:#ffffff;text-decoration:none;border-radius:5px;font-weight:bold;\">" +
                            "Go to Money Manager</a>" +
                            "<br><br>Best regards,<br>Money Manager Team";

            emailService.sendEmail(profile.getEmail(),"Daily Reminder : Add your Income and Expenses in M",body);
        }
        log.info("Job Finished :sendDailyIncomeExpenseReminder()");
    }
    
    @Scheduled(cron = "0 0 23 * * *",zone = "IST")
    public void sendDailyExpenseSummary(){
        log.info("Job Started : sendDailyExpenseSummary");
        List<ProfileEntity> profiles = profileRepository.findAll();
        for(ProfileEntity profile : profiles) {
            List<ExpenseDTO> todaysExpenses = expenseService.getExpensesForUserOnDate(profile.getId(), LocalDate.now());
            if(todaysExpenses != null) {
                StringBuilder table = new StringBuilder();

                table.append("<table style='width:100%;border-collapse:collapse;font-family:Arial,sans-serif;'>");

// Header row
                table.append("<tr style='background-color:#4CAF50;color:white;'>")
                        .append("<th style='padding:8px;border:1px solid #ddd;'>Date</th>")
                        .append("<th style='padding:8px;border:1px solid #ddd;'>Title</th>")
                        .append("<th style='padding:8px;border:1px solid #ddd;'>Category</th>")
                        .append("<th style='padding:8px;border:1px solid #ddd;'>Amount</th>")
                        .append("</tr>");

// Data rows
                BigDecimal total = BigDecimal.ZERO;

                for (ExpenseDTO expense : todaysExpenses) {
                    table.append("<tr>")
                            .append("<td style='padding:8px;border:1px solid #ddd;'>")
                            .append(expense.getDate())
                            .append("</td>")

                            .append("<td style='padding:8px;border:1px solid #ddd;'>")
                            .append(expense.getName())
                            .append("</td>")

                            .append("<td style='padding:8px;border:1px solid #ddd;'>")
                            .append(expense.getCategoryId()!= null ?  expense.getCategoryId().toString() : "N/A")
                            .append("</td>")

                            .append("<td style='padding:8px;border:1px solid #ddd;'>₹")
                            .append(expense.getAmount())
                            .append("</td>")
                            .append("</tr>");

                    total = total.add(expense.getAmount());
                }

// Total row
                table.append("<tr style='font-weight:bold;background-color:#f2f2f2;'>")
                        .append("<td colspan='3' style='padding:8px;border:1px solid #ddd;text-align:right;'>Total</td>")
                        .append("<td style='padding:8px;border:1px solid #ddd;'>₹")
                        .append(total)
                        .append("</td>")
                        .append("</tr>");

                table.append("</table>");

                String body =
                        "Hi " + profile.getFullName() + ",<br><br>" +

                                "Here’s a summary of your expenses for <b>" + LocalDate.now() + "</b>:<br><br>" +

                                table.toString() +

                                "<br><br>" +
                                "<a href=\"" + frontEndUrl + "\" " +
                                "style=\"display:inline-block;padding:10px 20px;" +
                                "background-color:#4CAF50;color:#ffffff;" +
                                "text-decoration:none;border-radius:5px;font-weight:bold;\">" +
                                "View in Money Manager</a>" +

                                "<br><br>" +
                                "Tip: Tracking expenses daily helps you stay in control of your finances 💡<br><br>" +

                                "Best regards,<br>" +
                                "<b>Money Manager Team</b>";

                emailService.sendEmail(profile.getEmail(),"Your daily Expense Summary",body);
            }
        }
        log.info("Job Finished :sendDailyExpenseSummary()");
    }
}
