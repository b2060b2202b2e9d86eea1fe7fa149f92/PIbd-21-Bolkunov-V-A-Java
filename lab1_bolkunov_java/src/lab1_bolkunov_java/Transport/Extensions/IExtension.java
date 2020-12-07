package lab1_bolkunov_java.Transport.Extensions;

import lab1_bolkunov_java.Transport.MotorShip;

import java.awt.*;

public interface IExtension {

    void setExtensionCount(int count);

    void Draw(Graphics g, MotorShip transport);

    static int convertExtensionCountToInt(ExtensionCount extensionCount) {
        switch (extensionCount) {
            case Single:
                return 1;
            case Double:
                return 2;
            case Triple:
                return 3;
            case Quadruple:
                return 4;
            default:
                return 0;
        }
    }

    static ExtensionCount convertIntToExtensionCount(int count) {
        switch (count) {
            case 1:
                return ExtensionCount.Single;
            case 2:
                return ExtensionCount.Double;
            case 3:
                return ExtensionCount.Triple;
            case 4:
                return ExtensionCount.Quadruple;
            default:
                return null;
        }
    }
}
