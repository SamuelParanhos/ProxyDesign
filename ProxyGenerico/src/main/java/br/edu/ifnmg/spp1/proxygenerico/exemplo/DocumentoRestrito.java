/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.spp1.proxygenerico.exemplo;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.nio.file.Path;

/**
 *
 * @author SamuelParanhos
 */
public class DocumentoRestrito implements Documento {

    private Path arquivo;
    private List<String> linhas;

    public DocumentoRestrito(Path caminho) { 
        this.arquivo = caminho;
        this.linhas = null;
    }

    @Override
    public void acessar(String usuario) {
 
        try {
            System.out.println("-> " + usuario + " está tentando acessar o documento. Carregando o conteúdo real...");
            
            // A leitura do arquivo só acontece quando o método acessar é chamado
            this.linhas = Files.readAllLines(this.arquivo);

            System.out.println("   Conteúdo do arquivo '" + arquivo.getFileName() + "':");
            for(String linha : this.linhas) {
                System.out.println("   " + linha);
            }
            
        } catch (IOException e) {
            System.out.println("ERRO: Falha na leitura de arquivo");
        }
    }
}
