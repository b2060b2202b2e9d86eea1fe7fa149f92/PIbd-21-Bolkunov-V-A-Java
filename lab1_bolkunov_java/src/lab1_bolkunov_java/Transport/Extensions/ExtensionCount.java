package lab1_bolkunov_java.Transport.Extensions;

public enum ExtensionCount //ДопПеречисл
{
    Single,
    Double,
    Triple,
    Quadruple;

    @Override
    public String toString() {
        switch (this) {
            case Single:
                return "Раз";
            case Double:
                return "Два";
            case Triple:
                return "Три";
            case Quadruple:
                return "Четыре";
            default:
                return "Ничего";
        }
    }
}