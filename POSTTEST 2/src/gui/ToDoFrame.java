package gui;

import user.User;
import main.ToDoList;
import main.ToDoTask;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class ToDoFrame extends JFrame {
    private ToDoList toDoList;
    private JPanel taskPanel;
    private JTextField newTaskField;
    private JButton addButton, removeButton;
    private List<JCheckBox> checkBoxes;

    public ToDoFrame(User user) {
        toDoList = new ToDoList(user.getUsername());
        checkBoxes = new ArrayList<>();

        setTitle("To-Do List");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(taskPanel);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3));

        newTaskField = new JTextField();
        addButton = new JButton("Add Task");
        removeButton = new JButton("Remove Task");

        panel.add(newTaskField);
        panel.add(addButton);
        panel.add(removeButton);

        add(panel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            String taskText = newTaskField.getText();
            if (!taskText.isEmpty()) {
                toDoList.addTask(taskText);
                newTaskField.setText(""); // Kosongkan input
                refreshTasks(); // Perbarui tampilan
            }
        });

        removeButton.addActionListener(e -> removeSelectedTasks());

        refreshTasks();
        setVisible(true);
    }

    private void refreshTasks() {
        taskPanel.removeAll();
        checkBoxes.clear();

        List<ToDoTask> tasks = toDoList.getTasks();
        for (ToDoTask task : tasks) {
            JPanel panel = new JPanel(new BorderLayout());
            JCheckBox checkBox = new JCheckBox();
            JLabel taskLabel = new JLabel(task.getDescription());

            panel.add(taskLabel, BorderLayout.WEST);
            panel.add(checkBox, BorderLayout.EAST);

            checkBoxes.add(checkBox);
            taskPanel.add(panel);
        }

        taskPanel.revalidate();
        taskPanel.repaint();
    }

    private void removeSelectedTasks() {
        List<ToDoTask> tasks = toDoList.getTasks();
        List<ToDoTask> tasksToRemove = new ArrayList<>();

        for (int i = 0; i < checkBoxes.size(); i++) {
            if (checkBoxes.get(i).isSelected()) {
                tasksToRemove.add(tasks.get(i));
            }
        }

        tasks.removeAll(tasksToRemove);
        refreshTasks(); // Perbarui tampilan setelah penghapusan
    }
}
