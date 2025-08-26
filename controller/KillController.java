package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KillController {
    
    // Método privado para identificar o sistema operacional
    private String os() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            return "Windows";
        } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("mac")) {
            return "Linux/Mac";
        } else {
            return "Unknown";
        }
    }
    
    // Método para listar processos ativos
    public void listaProcessos() {
        String os = os();
        String comando;
        
        if (os.equals("Windows")) {
            comando = "TASKLIST /FO TABLE";
        } else if (os.equals("Linux/Mac")) {
            comando = "ps -ef";
        } else {
            System.out.println("Sistema operacional não suportado: " + System.getProperty("os.name"));
            return;
        }
        
        try {
            Process process = Runtime.getRuntime().exec(comando);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String linha;
            
            System.out.println("\n=== PROCESSOS ATIVOS ===");
            System.out.println("Sistema Operacional: " + os);
            System.out.println("Comando executado: " + comando);
            System.out.println("=" .repeat(50));
            
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha);
            }
            
            reader.close();
            process.waitFor();
            
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao listar processos: " + e.getMessage());
        }
    }
    
    // Método para matar processo por PID
    public void mataPid(int pid) {
        String os = os();
        String comando;
        
        if (os.equals("Windows")) {
            comando = "TASKKILL /PID " + pid;
        } else if (os.equals("Linux/Mac")) {
            comando = "kill -9 " + pid;
        } else {
            System.out.println("Sistema operacional não suportado: " + System.getProperty("os.name"));
            return;
        }
        
        try {
            System.out.println("Executando comando: " + comando);
            Process process = Runtime.getRuntime().exec(comando);
            int exitCode = process.waitFor();
            
            if (exitCode == 0) {
                System.out.println("Processo com PID " + pid + " finalizado com sucesso!");
            } else {
                System.out.println("Falha ao finalizar processo com PID " + pid + ". Código de saída: " + exitCode);
            }
            
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao matar processo: " + e.getMessage());
        }
    }
    
    // Método para matar processo por nome
    public void mataNome(String nomeProcesso) {
        String os = os();
        String comando;
        
        if (os.equals("Windows")) {
            comando = "TASKKILL /IM " + nomeProcesso;
        } else if (os.equals("Linux/Mac")) {
            comando = "pkill -f " + nomeProcesso;
        } else {
            System.out.println("Sistema operacional não suportado: " + System.getProperty("os.name"));
            return;
        }
        
        try {
            System.out.println("Executando comando: " + comando);
            Process process = Runtime.getRuntime().exec(comando);
            int exitCode = process.waitFor();
            
            if (exitCode == 0) {
                System.out.println("Processo '" + nomeProcesso + "' finalizado com sucesso!");
            } else {
                System.out.println("Falha ao finalizar processo '" + nomeProcesso + "'. Código de saída: " + exitCode);
            }
            
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao matar processo: " + e.getMessage());
        }
    }
}