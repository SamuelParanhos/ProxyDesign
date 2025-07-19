/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.spp1.proxygenerico.exemplo;

import br.edu.ifnmg.spp1.proxygenerico.ProxyGenerico;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 *
 * @author SamuelParanhos
 */
public class Program {
    
    public static void main(String [] args){
     
        System.out.println("Sistema de acesso a Documentos com Proxy");
        
        //Definições para uso do Proxy
        
        //Definindo o caminho para arquivo que vai ser protegido (Proxy de Proteção)
        Path caminhoDoDocumento = Paths.get("UltraConfidencial.txt");
        
       
        //Criando um objeto generico
        Supplier<Documento> criadorDeDocumento = 
                () -> new DocumentoRestrito(caminhoDoDocumento);
        
        //Definindo a Segurança
        Predicate<Usuario> IDComAcesso = 
                (usuario) -> usuario.getID() == 123456789L;
        //Apenas o Usuario com esse ID pode acessar, ID Chefe ou Gerente....
        
        ProxyGenerico <Documento, Usuario> proxyDocumento = 
                new ProxyGenerico<>(criadorDeDocumento,IDComAcesso);
        
        //Exemplos de acesso
        
        Usuario usuarioAdm = new Usuario("Peter Park", 123456789L);
        Usuario usuarioComum = new Usuario ("Albert", 32343L);
        
        //Tentativa de acesso de um Usuario não autorizado
        try {
            Documento doc = proxyDocumento.getObjeto(usuarioComum);
            doc.acessar(usuarioComum.getUsario());
        } catch (SecurityException e) {
            System.err.println("FALHA ESPERADA: " + e.getMessage());
        }
        
    //Acesso de um Usuario autorizado
        try {
            Documento doc = proxyDocumento.getObjeto(usuarioAdm);
            doc.acessar(usuarioAdm.getUsario());
        } catch (SecurityException e) {
            System.err.println("ERRO INESPERADO: " + e.getMessage());
        }
    
    //Acessando denovo após o objeto ser criado
        try {
            Documento doc = proxyDocumento.getObjeto(usuarioAdm);
            // Note que a mensagem de "criando objeto" não deve aparecer de novo
            doc.acessar(usuarioAdm.getUsario());
        } catch (SecurityException e) {
            System.err.println("ERRO INESPERADO: " + e.getMessage());
        }
                
    }
    
}
