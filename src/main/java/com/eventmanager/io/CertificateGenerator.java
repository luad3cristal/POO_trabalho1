package com.eventmanager.io;

import com.eventmanager.model.event.Event;
import com.eventmanager.model.event.HybridEvent;
import com.eventmanager.model.participant.Participant;
import com.eventmanager.util.IdGenerator;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.File;
import java.io.IOException;

public class CertificateGenerator {

    public static String generateCertificateText(Participant participant, Event event, String certificateId) {
        return String.format("""
                CERTIFICATE OF PARTICIPATION
                
                Certificate ID: %s

                This is to certify that
                %s (CPF: %s)
                participated in the event:
                \"%s\" on %s.

                Mode: %s
                """,
                certificateId,
                participant.getName(),
                participant.getCpf(),
                event.getTitle(),
                event.getDate(),
                event instanceof HybridEvent hybrid
                        ? (hybrid.isOnline() && hybrid.isInPerson() ? "Hybrid" :
                          hybrid.isOnline() ? "Online" : "In-person")
                        : "In-person"
        );
    }

    public static void generateCertificatePDF(Participant participant, Event event, String outputPath) {
        String certificateId = IdGenerator.generateCertificateId();
        String text = generateCertificateText(participant, event, certificateId);

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream content = new PDPageContentStream(document, page)) {
                content.beginText();
                content.setFont(PDType1Font.TIMES_ROMAN, 14);
                content.setLeading(20f);
                content.newLineAtOffset(70, 700);

                for (String line : text.split("\n")) {
                    content.showText(line);
                    content.newLine();
                }

                content.endText();
            }

            File file = new File(outputPath);
            document.save(file);
            System.out.println("Certificate PDF saved to: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Failed to create PDF: " + e.getMessage());
        }
    }
}