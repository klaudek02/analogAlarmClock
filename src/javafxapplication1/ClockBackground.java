package javafxapplication1;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

class ClockBackground extends ResizableCanvas {

    GraphicsContext gc = getGraphicsContext2D();
    public void drawClock() {
        int smallTick = 7;
        int mediumTick = 20;
        int longTick = 35;
        gc.setStroke(Color.BLACK);
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        double r = Math.min(gc.getCanvas().getWidth(), gc.getCanvas().getHeight()) / 2 - 10;
        double x = 0.5 * gc.getCanvas().getWidth();
        double y = 0.5 * gc.getCanvas().getHeight();
        gc.strokeOval(x-r, y-r, 2*r, 2*r);
        for (int i = 1; i <= 60; i++) {
            int len = smallTick;
            if (i % 15 == 0) {
                len = longTick;
            } else if (i % 5 == 0) {
                len = mediumTick;
            }
            double kat = Math.PI*(0.5 - i/30.0);
            gc.strokeLine((float) (x + Math.cos(kat) * r),(float) (y - Math.sin(kat) * r),
                    (float) (x + Math.cos(kat) * (r - len)),(float) (y - Math.sin(kat) * (r - len)));
        }
    }
    @Override
    public void resize(double width, double height) {
        super.setWidth(width);
        super.setHeight(height);
        drawClock();
    }
}
