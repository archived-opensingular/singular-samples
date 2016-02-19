package br.net.mirante.singular.form.mform;

/**
 * Representa os flags booleanos e suas respectivas posições em um mapa de bits.
 * Usa o mapa para economizar memoria;
 *
 * @author Daniel C. Bordin
 */
public enum InstanceFlags {

    RemovendoInstancia, IsAtributo;

    private int bit;

    public int bit() {
        return bit;
    }

    static {
        // Escolha um bit para flag
        int pos = 0;
        for (InstanceFlags flag : InstanceFlags.values()) {
            flag.bit = 1 << pos;
            pos++;
        }
    }

}
