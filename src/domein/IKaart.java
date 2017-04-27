/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

/**
 *
 * @author timalenus
 */
public interface IKaart {
    public int getType(); 
    public int getWaarde(); 
    @Override
    public String toString();
}
