import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
public class SettingPan extends JPanel
{

    JSlider slider;
    FlowLayout layout; 
    TickLoop tickLoop;
    public SettingPan(TickLoop tickLoop)
    {
        this.tickLoop = tickLoop;
        makePanel();
        setVisible(true);
    }

    public void stateChanged(ChangeEvent e) {
    }
    
    private void makePanel(){
        layout = new FlowLayout(FlowLayout.LEFT);
        slider = new JSlider(5, 500, tickLoop.getSpeed());
        slider.setSize(50, 20);
        setLayout(layout);
        add(new JLabel("Clock Speed:"));
        add(slider); 
        slider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    tickLoop.setSpeed( slider.getValue() );
                }
            });

    }

}
