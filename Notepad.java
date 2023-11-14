import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicMenuBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Notepad implements ActionListener {
    JFrame file;
    JTextArea textArea;
    JMenuBar jMenuBar;
    JMenu file_set,edit,format,help;
    JMenuItem newItem,openItem,saveItem,cutItem,copyItem,pasteItem,selectAll, bg, fg;
    Notepad(){
        file=new JFrame();
        textArea=new JTextArea();
        textArea.setBounds(4,40,996,996);
        file.add(textArea);

        jMenuBar=new JMenuBar();
        jMenuBar.setBounds(4,5,996,30);
        jMenuBar.setForeground(Color.BLUE);
        jMenuBar.setUI(new BasicMenuBarUI());
        jMenuBar.setBackground(Color.blue);
        file.add(jMenuBar);


        file_set=new JMenu("File");
        edit=new JMenu("Edit");
        format=new JMenu("Format");
        help=new JMenu("Help");


        jMenuBar.add(file_set);
        jMenuBar.add(edit);
        jMenuBar.add(format);
        jMenuBar.add(help);


        newItem=new JMenuItem("New file");
        openItem=new JMenuItem("Open file");
        saveItem=new JMenuItem("Save file ");

        file_set.add(newItem);
        file_set.add(openItem);
        file_set.add(saveItem);

        cutItem=new JMenuItem("Cut");
        pasteItem=new JMenuItem("Paste");
        copyItem=new JMenuItem("Copy");
        selectAll=new JMenuItem("Select all");

        edit.add(cutItem);
        edit.add(copyItem);
        edit.add(pasteItem);
        edit.add(selectAll);

        bg=new JMenuItem("Set Background");
        fg=new JMenuItem("Set Foreground");


//        ADDEVENTLISTENER
        copyItem.addActionListener(this);
        pasteItem.addActionListener(this);
        copyItem.addActionListener(this);
        selectAll.addActionListener(this);
        fg.addActionListener(this);
        bg.addActionListener(this);
        newItem.addActionListener(this);
        saveItem.addActionListener(this);
        openItem.addActionListener(this);
        format.add(bg);
        format.add(fg);


        file.setLayout(null);
        file.setTitle("Notatnik");
        file.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        file.getContentPane().setBackground(Color.gray);
        file.setSize(1000,1000);
        file.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(cutItem)) {
            textArea.cut();
        } else if (source.equals(pasteItem)) {
            textArea.paste();
        } else if (source.equals(copyItem)) {
            textArea.copy();
        } else if (source.equals(selectAll)) {
            textArea.selectAll();
        }else if (source.equals(bg)) {
            Color color = JColorChooser.showDialog(file, "Select a Color", Color.WHITE);
            textArea.setBackground(color);}

        else if (source.equals(fg)) {
            Color color= JColorChooser.showDialog(file,"Select a Color",Color.BLACK);
            textArea.setForeground(color);

        } else if (source.equals(newItem)) {
            textArea.setText("");
            textArea.setBackground(Color.white);
            textArea.setForeground(Color.black);
        }else if (source.equals(openItem)) {
            JFileChooser jFileChooser = new JFileChooser();
            var option = jFileChooser.showOpenDialog(file);
            File f = jFileChooser.getSelectedFile();


            try {
                Scanner scanner = new Scanner(f);
                while (scanner.hasNextLine()) {
                    textArea.append("\n" + scanner.nextLine());
                }
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }



        } else if (source.equals(saveItem)) {
                JFileChooser jFileChooser=new JFileChooser();
                jFileChooser.setDialogTitle("Specify a file to save");
                var option=jFileChooser.showSaveDialog(file);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    File f = jFileChooser.getSelectedFile();
                    String text = textArea.getText();

                    try (FileWriter myfile = new FileWriter(f)) {
                        myfile.write(text);
                        System.out.println("Successful: Text saved to " + f.getAbsolutePath());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                        System.out.println("Error: Unable to write to file");
                    }
                } catch (Exception exp) {
                    exp.printStackTrace();
                    System.out.println("Error: Unable to save file");
                }
                }

        }
    }}
