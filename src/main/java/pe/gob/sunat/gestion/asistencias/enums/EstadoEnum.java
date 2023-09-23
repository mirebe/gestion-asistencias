package pe.gob.sunat.gestion.asistencias.enums;


public enum EstadoEnum {

    ACTIVO(1),
    INACTIVO(0);

    private int valor;

    EstadoEnum(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static String getStringValueFromInt(int i) {
        for (EstadoEnum estado : EstadoEnum.values()) {
            if (estado.getValor() == i) {
                return estado.toString();
            }
        }

        throw new IllegalArgumentException("el numero dado no se encontro.");
    }

}
