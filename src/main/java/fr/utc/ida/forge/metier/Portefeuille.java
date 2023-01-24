/*
 * Copyright 2023 Croain - Bernault
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.utc.ida.forge.metier;

import fr.utc.ida.forge.exception.VenteImpossibleException;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author perussel
 */
public class Portefeuille {

    HashMap<Action, Integer> mapLignes;

    public Portefeuille() {
        this.mapLignes = new HashMap<>();
    }

    
    /** 
     * @param a
     * @param qte
     */
    public void acheter(Action a, int qte){
        if (this.mapLignes.containsKey(a)){
            Integer qtyAlreadyPossessed = this.mapLignes.get(a);
            this.mapLignes.replace(a, qtyAlreadyPossessed + qte); 
        } else{
            this.mapLignes.put(a, qte);
        }
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Mes actions : \n");
        for (Map.Entry<Action, Integer> entry : this.mapLignes.entrySet()) {
            sb.append(entry.getKey());
            sb.append(" : ");
            sb.append(entry.getValue());
            sb.append("\n");
        }
        return sb.toString();
    }

    
    /** 
     * @param a
     * @param qte
     * @throws VenteImpossibleException
     */
    public void vendre(Action a, int qte) throws VenteImpossibleException {
        if (this.mapLignes.containsKey(a)){
            Integer qtyAlreadyPossessed = this.mapLignes.get(a);
            if (qtyAlreadyPossessed - qte < 0){
                throw new VenteImpossibleException();
            }
            else{
                this.mapLignes.replace(a, qtyAlreadyPossessed - qte); 
            }
        } else {
            throw new VenteImpossibleException();
        }
    }

    
    /** 
     * @param a
     * @return String
     */
    public String getQteAchat(Action a){
        int quantite = 0;
        if(this.mapLignes.containsKey(a)){
            quantite = this.mapLignes.get(a);
            return String.format("Vous avez %i"+ " de l'action %s", quantite, a.getLibelle());
        }
        return String.format("Cette action n'est pas contenue dans le portefeuille");
    }

    public float valeur(Jour j) {
        float total = 0;
        for (Map.Entry<Action, Integer> entry : this.mapLignes.entrySet()) {
            total += entry.getValue() * entry.getKey().valeur(j);
        }
        return total;
    }
}
