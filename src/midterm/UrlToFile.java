package midterm;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UrlToFile {
    public static void main(String[] args) {
        for (int c = 1; c <= 50; c++) {
            for (int d = 1; d <= 50; d++) {
                String url = "http://nas.hoony.me:9998/midtermPractice/file%20";
                working(url
                        + "(c=" + c + ")_(d=" + d + ").txt", "file (c=" + c + ")_(d=" + d + ").txt");
                working(url
                        + "(c=" + c + ")_<d=" + d + ">.txt", "file (c=" + c + ")_<d=" + d + ">.txt");
                working(url
                        + "<c=" + c + ">_(d=" + d + ").txt", "file <c=" + c + ">_(d=" + d + ").txt");
                working(url
                        + "<c=" + c + ">_<d=" + d + ">.txt", "file <c=" + c + ">_<d=" + d + ">.txt");
            }
        }
    }

    public static void writeNumbersInFormat(String outputPath, List<String> allNumbers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            int lineCount = 0;

            for (String numbers : allNumbers) {
                if (lineCount == 300) {
                    lineCount = 0; // 줄 수를 재설정
                }

                writer.write(numbers + " "); // 숫자를 파일에 쓰고, 공백으로 구분
                lineCount++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("An error occurred while writing to the file.");
            e.printStackTrace();
        } catch(IOException e){

        }
    }


    public static void working(String str, String str2){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new URL(str).openStream()), 65536)) {
            String newFilePath = "C:\\Users\\leesu\\IdeaProjects\\networkprogramming\\src\\midterm\\Test" + File.separator + str2; // 새 파일의 경로 생성
            try {
                File file = new File(newFilePath);
                // 파일이 이미 존재하지 않는 경우에만 새 파일을 생성합니다.
                if (!file.exists()) {
                    if (file.createNewFile()) {
                        System.out.println("File created: " + file.getName());
                    } else {
                        System.out.println("File already exists.");
                    }
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            List<String> allNumbers = new ArrayList<>(); // 모든 숫자들을 저장할 리스트
            String line;
            while ((line = br.readLine()) != null) {
                allNumbers.add(line);
                allNumbers.add("\n");
            }
            writeNumbersInFormat(newFilePath, allNumbers);
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }
}
