import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Component;
import javax.swing.text.*;
/**
 * Write a description of class GUI here.
 * 
 * @author (Asa Swan) 
 * @version (4/8/2014)
 */
public class GUI extends JFrame implements Actor
{
    private int time;
    private int day;
    private FlightController fc;
    private JTextArea textArea;
    private JPanel north;
    private JPanel west;
    private JLabel timeLabel;
    private JLabel statAvgGateTimeLabel;
    private final static String newLine = "\n";
    /** Tick Speed Changer */
    private SettingPan span;
    private TickLoop tickLoop;

    /**
     * Creates the GUI and shows it on the screen
     */
    public GUI(FlightController fc, TickLoop tickLoop)
    {
        this.fc = fc;
        this.tickLoop = tickLoop;
        this.span = new SettingPan(tickLoop);
        makeFrame();
    }

    /**
     * Display the current state
     */
    public void act(int tick)
    {
        time = tick;
        prepareUpdate();
        getContentPane().repaint();
    }

    /**
     * Create the Swing frame and its content.
     */
    public void makeFrame()
    {
        setTitle("CCC Airport Simulator");
        makeMenuBar();

        /**Frame container and layout*/
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(6, 6));

        /**North panel*/
        north = new JPanel();
        north.setLayout(new GridLayout(0,1));
        
        //Time label
        timeLabel = new JLabel("  Time: " + time + "  Day: " + day);
        north.add(timeLabel);
        
        //Tick Speed Pan
        north.add(span);
        
        contentPane.add(north, BorderLayout.NORTH);

        /**West panel*/
        west = new JPanel();
        //BoxLayout grid = new BoxLayout(west, BoxLayout.Y_AXIS);
        west.setLayout(new BoxLayout(west, BoxLayout.Y_AXIS));

        //Status Pane with text area
        textArea = new JTextArea(15,30);
        JScrollPane statusPane = new JScrollPane(textArea);
        new SmartScroller(statusPane);
        textArea.setEditable(false);
        west.add(statusPane);

        //Average Gate Time Label
        statAvgGateTimeLabel = new JLabel("Average Gate Time: ");
        west.add(statAvgGateTimeLabel);

        contentPane.add(west, BorderLayout.WEST);

        //Image pane with graphics

        pack();
        setVisible(true);
    }

    /**
     * Create the main frame's menu bar.
     * @param frame   The frame that the menu bar should be added to.
     */
    public void makeMenuBar()
    {
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);

        // create the File menu
        JMenu fileMenu = new JMenu("File");
        menubar.add(fileMenu);

        JMenuItem playItem = new JMenuItem("Play");
        playItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { fc.play(); }
            });
        fileMenu.add(playItem);

        JMenuItem pauseItem = new JMenuItem("Pause");
        pauseItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { tickLoop.pause(); }
            });
        fileMenu.add(pauseItem);

        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { fc.quit(); }
            });
        fileMenu.add(quitItem);
    }

    /**
     * Prepare for a new round of updates (repaints) to the GUI
     */
    public void prepareUpdate()
    {
        timeUpdate();     
    }

    /**
     * Quit function: quit the application.
     */
    public void quit()
    {
        System.exit(0);
    }

    public void statusUpdate(String s)
    {
        textArea.append("[" + convertTime(time) + "] " + s + newLine);
    }

    public void timeUpdate()
    {
        String newTime = convertTime(time);
        timeLabel.setText("  Time: " + newTime + "    Day: " + day);
    }

    public void statsUpdate(int avgGateTime)
    {
        statAvgGateTimeLabel.setText("Average Gate Time: " + avgGateTime);
    }

    public String convertTime(int m)
    {        
        //Calculating days (1440 minutes in a 24 hour day)
        day = m / 1440;
        //If it's been over 24 hours, reset minutes to 0
        if(day < 1)
            m -= (1440 * day);

        //Calculating hours
        int h = m / 60;
        //Reseting minutes to 0 if it's been more than one hour
        if(h > 0)
            m -= (60 * h);
        //Resetting hours to 0 if it's been more than 24 hours
        if(h > 23)
            h -= (24 * day);

        //Convertnig h to a String
        String h2;
        //Adding a 0 in front of the hours if it's less than 10
        if(h < 10)
            h2 = "0" + h;
        else
            h2 = "" + h;

        //Converting m to a String
        String m2;
        //Adding a 0 in front of the minutes if it's less than 10
        if(m < 10)
            m2 = "0" + m;
        else
            m2 = "" + m;

        return h2 + ":" + m2;
    }

    /**
     *  Found at http://tips4java.wordpress.com/2013/03/03/smart-scrolling/
     *  
     *  The SmartScroller will attempt to keep the viewport positioned based on
     *  the users interaction with the scrollbar. The normal behaviour is to keep
     *  the viewport positioned to see new data as it is dynamically added.
     *
     *  Assuming vertical scrolling and data is added to the bottom:
     *
     *  - when the viewport is at the bottom and new data is added,
     *    then automatically scroll the viewport to the bottom
     *  - when the viewport is not at the bottom and new data is added,
     *    then do nothing with the viewport
     *
     *  Assuming vertical scrolling and data is added to the top:
     *
     *  - when the viewport is at the top and new data is added,
     *    then do nothing with the viewport
     *  - when the viewport is not at the top and new data is added, then adjust
     *    the viewport to the relative position it was at before the data was added
     *
     *  Similiar logic would apply for horizontal scrolling.
     */
    public class SmartScroller implements AdjustmentListener
    {
        public final static int HORIZONTAL = 0;
        public final static int VERTICAL = 1;

        public final static int START = 0;
        public final static int END = 1;

        private int viewportPosition;

        private JScrollBar scrollBar;
        private boolean adjustScrollBar = true;

        private int previousValue = -1;
        private int previousMaximum = -1;

        /**
         *  Convenience constructor.
         *  Scroll direction is VERTICAL and viewport position is at the END.
         *
         *  @param scrollPane the scroll pane to monitor
         */
        public SmartScroller(JScrollPane scrollPane)
        {
            this(scrollPane, VERTICAL, END);
        }

        /**
         *  Convenience constructor.
         *  Scroll direction is VERTICAL.
         *
         *  @param scrollPane the scroll pane to monitor
         *  @param viewportPosition valid values are START and END
         */
        public SmartScroller(JScrollPane scrollPane, int viewportPosition)
        {
            this(scrollPane, VERTICAL, viewportPosition);
        }

        /**
         *  Specify how the SmartScroller will function.
         *
         *  @param scrollPane the scroll pane to monitor
         *  @param scrollDirection indicates which JScrollBar to monitor.
         *                         Valid values are HORIZONTAL and VERTICAL.
         *  @param viewportPosition indicates where the viewport will normally be
         *                          positioned as data is added.
         *                          Valid values are START and END
         */
        public SmartScroller(JScrollPane scrollPane, int scrollDirection, int viewportPosition)
        {
            if (scrollDirection != HORIZONTAL
            &&  scrollDirection != VERTICAL)
                throw new IllegalArgumentException("invalid scroll direction specified");

            if (viewportPosition != START
            &&  viewportPosition != END)
                throw new IllegalArgumentException("invalid viewport position specified");

            this.viewportPosition = viewportPosition;

            if (scrollDirection == HORIZONTAL)
                scrollBar = scrollPane.getHorizontalScrollBar();
            else
                scrollBar = scrollPane.getVerticalScrollBar();

            scrollBar.addAdjustmentListener( this );

            //  Turn off automatic scrolling for text components

            Component view = scrollPane.getViewport().getView();

            if (view instanceof JTextComponent)
            {
                JTextComponent textComponent = (JTextComponent)view;
                DefaultCaret caret = (DefaultCaret)textComponent.getCaret();
                caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
            }
        }

        @Override
        public void adjustmentValueChanged(final AdjustmentEvent e)
        {
            SwingUtilities.invokeLater(new Runnable()
                {
                    public void run()
                    {
                        checkScrollBar(e);
                    }
                });
        }

        /*
         *  Analyze every adjustment event to determine when the viewport
         *  needs to be repositioned.
         */
        private void checkScrollBar(AdjustmentEvent e)
        {
            //  The scroll bar listModel contains information needed to determine
            //  whether the viewport should be repositioned or not.

            JScrollBar scrollBar = (JScrollBar)e.getSource();
            BoundedRangeModel listModel = scrollBar.getModel();
            int value = listModel.getValue();
            int extent = listModel.getExtent();
            int maximum = listModel.getMaximum();

            boolean valueChanged = previousValue != value;
            boolean maximumChanged = previousMaximum != maximum;

            //  Check if the user has manually repositioned the scrollbar

            if (valueChanged && !maximumChanged)
            {
                if (viewportPosition == START)
                    adjustScrollBar = value != 0;
                else
                    adjustScrollBar = value + extent >= maximum;
            }

            //  Reset the "value" so we can reposition the viewport and
            //  distinguish between a user scroll and a program scroll.
            //  (ie. valueChanged will be false on a program scroll)

            if (adjustScrollBar && viewportPosition == END)
            {
                //  Scroll the viewport to the end.
                scrollBar.removeAdjustmentListener( this );
                value = maximum - extent;
                scrollBar.setValue( value );
                scrollBar.addAdjustmentListener( this );
            }

            if (adjustScrollBar && viewportPosition == START)
            {
                //  Keep the viewport at the same relative viewportPosition
                scrollBar.removeAdjustmentListener( this );
                value = value + maximum - previousMaximum;
                scrollBar.setValue( value );
                scrollBar.addAdjustmentListener( this );
            }

            previousValue = value;
            previousMaximum = maximum;
        }
    }

}