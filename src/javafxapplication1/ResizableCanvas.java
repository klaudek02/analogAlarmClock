package javafxapplication1;
import javafx.scene.canvas.Canvas;
public class ResizableCanvas extends Canvas
{
    @Override
    public double minHeight(double width) {
        return 1;
    }
    @Override
    public double maxHeight(double width) {
        return Double.MAX_VALUE;
    }
    @Override
    public double prefHeight(double width) {
        return minHeight(width);
    }
    @Override
    public double minWidth(double height) {
        return 1;
    }
    @Override
    public double maxWidth(double height) {
        return Double.MAX_VALUE;
    }
    @Override
    public boolean isResizable() {
        return true;
    }

}
