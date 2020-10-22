package lab1_bolkunov_java;

public enum PipeCount //ДопПеречисл
{
    Single,
    Double,
    Triple,
    Quadruple;

    @Override
    public String toString() {
        switch (this) {
            case Single:
                return "Одна труба";
            case Double:
                return "Две трубы";
            case Triple:
                return "Три трубы";
            case Quadruple:
                return "Четыре трубы";
            default:
                return "Ничего";
        }
    }
}