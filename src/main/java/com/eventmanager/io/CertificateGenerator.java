package com.eventmanager.io;

import com.eventmanager.model.event.Event;
import com.eventmanager.model.event.HybridEvent;
import com.eventmanager.model.participant.Participant;
import com.eventmanager.util.DateUtils;
import com.eventmanager.util.IdGenerator;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CertificateGenerator {

    public static String generateCertificateText(Participant participant, Event event, String certificateId) {
        String formattedDate = DateUtils.formatDate(DateUtils.parseDateFlexible(event.getDate()));

        return String.format("""
                CERTIFICATE OF PARTICIPATION

                This is to certify that: 
                
                %s - CPF: %s

                has actively participated in the event:

                "%s"

                held on %s, with a capacity of %d participants,
                organized as a %s event.

                The participation of the above-mentioned individual
                was of great value to the success of the activity.

                Certificate ID: %s
                """,
                participant.getName(),
                participant.getCpf(),
                event.getTitle(),
                formattedDate != null ? formattedDate : "Invalid Date",
                event.getCapacity(),
                (event instanceof HybridEvent hybrid)
                        ? (hybrid.isOnline() && hybrid.isInPerson() ? "hybrid" :
                        hybrid.isOnline() ? "online" : "in-person")
                        : "in-person",
                certificateId
        );
    }

    public static void generateCertificatePDF(Participant participant, Event event, String outputPath) {
        String certificateId = IdGenerator.generateCertificateId();
        String text = generateCertificateText(participant, event, certificateId);

        File file = new File(outputPath);

        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            boolean created = parentDir.mkdirs();
            if (!created) {
                System.err.println("Não foi possível criar o diretório para salvar o certificado.");
                return;
            }
        }

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));
            document.addPage(page);

            try (PDPageContentStream content = new PDPageContentStream(document, page)) {
                content.beginText();
                content.setFont(PDType1Font.TIMES_ROMAN, 14);
                content.setLeading(24f);

                float margin = 50;
                float yStart = page.getMediaBox().getHeight() - margin;
                content.newLineAtOffset(0, yStart);

                List<String> lines = List.of(text.split("\n"));
                float pageWidth = page.getMediaBox().getWidth();

                for (String line : lines) {
                    float textWidth = PDType1Font.TIMES_ROMAN.getStringWidth(line) / 1000 * 14;
                    float xOffset = (pageWidth - textWidth) / 2;
                    content.newLineAtOffset(xOffset, 0);
                    content.showText(line);
                    content.newLine();
                    content.newLineAtOffset(-xOffset, 0);
                }

                content.endText();
            }

            document.save(file);
            System.out.println("Certificate PDF saved to: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Failed to create PDF: " + e.getMessage());
        }
    }
}
