package lab1_bolkunov_java.Transport.Extensions;

public enum ExtensionCount //�����������
{
    Single,
    Double,
    Triple,
    Quadruple;

    @Override
    public String toString() {
        switch (this) {
            case Single:
                return "���";
            case Double:
                return "���";
            case Triple:
                return "���";
            case Quadruple:
                return "������";
            default:
                return "������";
        }
    }
}