/*
 * Copyright (C) 2025 SamuelParanhos
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

    

package br.edu.ifnmg.spp1.proxygenerico;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 *
 * @author SamuelParanhos
 */

/**
 * Proxy Genérico com foco nas aplicações do Proxy Virtual e Proxy de Proteção
 * @param <T> O tipo do objeto real
 * @param <U> O tipo do objeto para validação da segurança
 */
public class ProxyGenerico<T, U>{
    
    private T objetoReal;
    private final Supplier<T> criadorObjeto;
    private final Predicate<U> seguranca;//Funciona como interfece mas usando booleano

    public ProxyGenerico(Supplier<T> criadorObjeto, Predicate<U> seguranca) {
        this.objetoReal = null;
        this.criadorObjeto = criadorObjeto;
        this.seguranca = seguranca;
    }
    
    public T getObjeto(U regraDeSegurança){
        
        //Verifica se passou pelo sistema de segurança(Proxy de Proteção)
        if(!seguranca.test(regraDeSegurança)){
            throw new SecurityException("Acesso Negado");
        }
        
        //Se passou, verifica se e necessário criar o objeto(Proxy Virtual)
        
        if(objetoReal == null){
            System.out.println("Criando objeto...");
            this.objetoReal = criadorObjeto.get();                  
        }
        
        else{
            System.out.println("Acesso Liberado");
        }
        
        return this.objetoReal;       
    }
   
}
