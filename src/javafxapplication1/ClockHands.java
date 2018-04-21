package javafxapplication1;


import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import java.util.Calendar;
import java.util.TimeZone;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class ClockHands extends ResizableCanvas {
    GraphicsContext gc = getGraphicsContext2D();
    private void draw() {  //odswiezanie zegara co 1s
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
                    drawHands();
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    public void drawHands() {
        int padding = 10;
        //promien oraz srodek zegara
        double r = Math.min(gc.getCanvas().getWidth(), gc.getCanvas().getHeight()) / 2 - padding;
        double x = 0.5 * gc.getCanvas().getWidth();
        double y = 0.5 * gc.getCanvas().getHeight();

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        //wskazowka sekundowa
        double second = (double) (calendar.get(Calendar.SECOND));
        double kat = Math.PI*(0.5 - second/30.0);
        gc.setStroke(Color.RED);

        gc.strokeLine(x,y,
                (double) (x + Math.cos(kat) * (r - 25)),
                (double) (y - Math.sin(kat) * (r - 25)));

        //wskazowka minutowa
        double minute = (double) (calendar.get(Calendar.MINUTE))
                + (double) (calendar.get(Calendar.SECOND)) / 60.0;
        kat = Math.PI*(0.5 - minute/30.0);

        gc.setStroke(Color.BLUE);
        gc.strokeLine(x,y,
                (double) (x + Math.cos(kat) * (r - 40)),
                (double) (y - Math.sin(kat) * (r - 40)));

        //wskazowka godzinowa
        double hour = (double) (calendar.get(Calendar.HOUR_OF_DAY) % 12)
                + (double) (calendar.get(Calendar.MINUTE)) / 60.0;

        kat = Math.PI*(0.5 - hour/6.0);
        gc.setStroke(Color.BLACK);
        gc.strokeLine(x,y,
                (double) (x + Math.cos(kat) * (r - 75)),
                (double) (y - Math.sin(kat)* (r - 75)));
                
    }
    @Override
    public void resize(double width, double height) {
        super.setWidth(width);
        super.setHeight(height);
        draw();
    }
}
