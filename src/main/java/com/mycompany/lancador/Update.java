/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lancador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 *
 * @author cristiano
 */
public class Update {

    void fazBackup() {

        try {
            String home = System.getenv("GOURMET_HOME");
            ProcessBuilder pb;
            // String caminho = new File("..").getCanonicalPath();
            String caminho = System.getenv("GOURMET_HOME").replace("\"", "");
            //Windows
            if (System.getProperty("os.name").contains("Windows")) {
                //Cria Pasta Backup
                File backup = new File(caminho + "\\backup\\");
                backup.mkdir();
                //Copia Pasta do sistema para o backup  windows
                // new ExecutaCmdWindows().execCommand("xcopy /E /S /I /Y \"" + caminho + "\\arquivos\" \"" + backup.getPath() + "\\arquivos\"");
                //Executa backup banco Windows
//                String[] c = {caminho + "\\pgsql\\bin\\pg_dump.exe ", "--host", "" + properties.getProperty("prop.host") + "",
//                    "--port", "5432", "--username", "postgres", "--no-password", "--format", "custom", "--blobs", "--verbose", "--file", backup.getPath() + "\\bdbackup.backup", "afagourmet"};
//                for (String s : c) {
//                    System.out.println("comando backup" + s);
//                }

//                pb = new ProcessBuilder(bdPath + "\\pgsql\\bin\\pg_dump.exe ", "-i", "-h", "" + properties.getProperty("prop.host") + "",
//                        "-p", "5432", "-U", "postgres", "-F", "c", "-b", "-v", "-f", backup.getPath() + "\\bdbackup.backup", "afagourmet");
                List<String> comands = new ArrayList<>();
                comands.add(caminho + "\\pgsql\\bin\\pg_dump.exe");
                System.out.println("caminho do exe" + caminho + "\\pgsql\\bin\\pg_dump.exe");

                comands.add("--host");
                comands.add("localhost");
                comands.add("--port");
                comands.add("5432");
                comands.add("--username");
                comands.add("postgres");
                comands.add("--no-password");
                comands.add("--format");
                comands.add("custom");
                comands.add("--blobs");
                comands.add("--verbose");
                comands.add("--file");
                comands.add(backup.getPath() + "\\bdbackup.backup");
                comands.add("afagourmet");
                pb = new ProcessBuilder(comands);
                pb.environment().put("PGPASSWORD", "Pkg1522pam");
                pb.start();

                System.out.println("Backup do bd realizado com sucesso");

                //Download Arquivos
                //*****   String urlDownload = A.URL + "launcher/versao/" + pc.getCodpla().getCodpro().getCodpro() + "-" + versaoNova.getNumerover() + ".zip";
                ///****    String arquivoBaixado = caminho + "\\" + pc.getCodpla().getCodpro().getCodpro() + "-" + versaoNova.getNumerover() + ".zip";
                //***   fazDownload(urlDownload, arquivoBaixado);
                //**    unZip(new File(arquivoBaixado));
                //  ManipulaZip.unZipIt(arquivoBaixado, caminho);
                //Nome BD , Usuario , Senha BD (Executa script)
                File up = new File(caminho + "\\arquivos\\update.sql");
                if (up.isFile()) {
                    Database.executeScript("afagourmet", "postgres", "Pkg1522pam", up.getPath());
                }
                up.delete();

                File war = new File(caminho + "\\arquivos\\WSafagourmet7.war");
                if (war.isFile()) {
                    new ExecutaCmdWindows().execCommand("xcopy /E /S /I /Y \"" + war.getPath() + "\" \"" + caminho + "\\apache\\webapps \"");
                }
                war.delete();
            } else {
                //Cria Pasta Backup
                File backup = new File(caminho + "/backup/");
                backup.mkdir();
                //Copia Pasta do sistema para o backup  Linux
                // new ExecutaShellLinux().executeCommand("cp -r " + caminho + "/arquivos " + backup.getPath());
                //Executa backup banco Linux
//                pb = new ProcessBuilder("pg_dump", "-i", "-h", "localhost",
//                        "-p", "5432", "-U", "postgres", "-F", "c", "-b", "-v", "-f", backup.getPath() + "/bdbackup.backup", "afagourmet");
                List<String> comands = new ArrayList<>();
                comands.add(caminho + "/pgsql/bin/pg_dump");
                comands.add("-h");
                comands.add("localhost");
                comands.add("-p");
                comands.add("5432");
                comands.add("-U");
                comands.add("postgres");
                comands.add("-F");
                comands.add("c");
                comands.add("-b");
                comands.add("-v");
                comands.add("-f");
                comands.add(backup.getPath() + "/bdbackup.backup");
                comands.add("afagourmet");
                pb = new ProcessBuilder(comands);
                pb.environment().put("PGPASSWORD", "Pkg1522pam");
                pb.start();
                System.out.println("Backup do bd realizado com sucesso");

                ///****  String urlDownload = A.URL + "launcher/versao/" + pc.getCodpla().getCodpro().getCodpro() + "-" + versaoNova.getNumerover() + ".zip";
                //**** String arquivoBaixado = caminho + "/" + pc.getCodpla().getCodpro().getCodpro() + "-" + versaoNova.getNumerover() + ".zip";
                //*** fazDownload(urlDownload, arquivoBaixado);
                //**     unZip(new File(arquivoBaixado));
                //Nome BD , Usuario , Senha BD (Executa script)
                File up = new File(caminho + "/arquivos/update.sql");
                System.out.println("local do script" + caminho + "/arquivos/update.sql");
                if (up.isFile()) {
                    Database.executeScript("afagourmet", "postgres", "Pkg1522pam", up.getPath());
                }
                //  up.delete();
                File war = new File(caminho + "/arquivos/WSafagourmet7.war");
                if (war.isFile()) {
                    new ExecutaShellLinux().executeCommand("cp -r " + caminho + "/apache/webapps/");
                }
                war.delete();
            }

        } catch (Exception ex) {
            ex.printStackTrace();

            // restoreBd();
        }

    }

    private void fazDownload(String urlArquivo, String caminhoEName) {
        try {
            //cria URL
//            URL url1 = new URL(protocolo, endereco, arquivo);
            URL url1 = new URL(urlArquivo);
            //abre uma conexao na url criada àcima
            URLConnection con = url1.openConnection();

            //tenta conectar.
            con.connect();
            //arquivo de saida
//            FileOutputStream fileOut = new FileOutputStream("/home/mario/teste/JSFImmediate.zip");
            FileOutputStream fileOut = new FileOutputStream(caminhoEName);
            int c = 0;
            do {
                //le o byte
                c = con.getInputStream().read();

                //escreve o byte no arquivo saida
                fileOut.write(c);

            } while (c != -1);

            //fecha o arquivo de saida
            fileOut.close();

            System.out.println("Arquivo baixado com sucesso");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void unZip(File file) {
        System.out.println("arquivo para descompactar" + file.getAbsolutePath());
        byte[] buffer = new byte[1024];

        try {

            // Cria o input do arquivo ZIP
            ZipInputStream zinstream = new ZipInputStream(new FileInputStream(file));

            // Pega a proxima entrada do arquivo
            ZipEntry zentry = zinstream.getNextEntry();
            // Enquanto existir entradas no ZIP
            while (zentry != null) {
                // Pega o nome da entrada
                String entryName = zentry.getName();
                // Cria o output do arquivo , Sera extraido onde esta rodando a classe
                if (zentry.isDirectory()) {
                    File folder = null;
                    if (System.getProperty("os.name").contains("Windows")) {
                        folder = new File(file.getParentFile().getPath() + "\\" + entryName);
                    } else {
                        folder = new File(file.getParentFile().getPath() + "/" + entryName);
                    }

                    folder.mkdir();
                } else {
                    FileOutputStream outstream = new FileOutputStream(new File(file.getAbsolutePath().replace(file.getName(), entryName)));
                    int n;
                    // Escreve no arquivo
                    while ((n = zinstream.read(buffer)) > -1) {
                        outstream.write(buffer, 0, n);
                    }
                    // Fecha arquivo
                    outstream.close();
                }

                // Fecha entrada e tenta pegar a proxima
                zinstream.closeEntry();
                zentry = zinstream.getNextEntry();
            }

            // Fecha o zip como um todo
            zinstream.close();

            //    file.delete();
            System.out.println("Done");
        } catch (IOException ex) {
            System.out.println("erro zip" + ex.getMessage());
            ex.printStackTrace();
        }
    }

}
