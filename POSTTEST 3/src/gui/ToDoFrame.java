package gui;

import user.User;
import main.ToDoList;
import main.Task;
import main.PersonalTask;
import main.WorkTask;
import main.UrgentTask;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class ToDoFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JPanel personalTaskPanel, workTaskPanel, urgentTaskPanel;
    private ToDoList personalToDoList, workToDoList, urgentToDoList;

    public ToDoFrame(User user) {
        personalToDoList = new ToDoList(user.getUsername());
        workToDoList = new ToDoList(user.getUsername());
        urgentToDoList = new ToDoList(user.getUsername());

        setTitle("To-Do List");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Dropdown untuk memilih jenis task
        JComboBox<String> taskTypeDropdown = new JComboBox<>(new String[]{"Personal", "Work", "Urgent"});
        taskTypeDropdown.addActionListener(e -> {
            String selectedType = (String) taskTypeDropdown.getSelectedItem();
            switch (selectedType) {
                case "Work":
                    cardLayout.show(mainPanel, "Work");
                    break;
                case "Urgent":
                    cardLayout.show(mainPanel, "Urgent");
                    break;
                default:
                    cardLayout.show(mainPanel, "Personal");
                    break;
            }
        });

        // Panel utama dengan CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Panel untuk setiap jenis task
        personalTaskPanel = createTaskPanel(personalToDoList, "Personal Task");
        workTaskPanel = createTaskPanel(workToDoList, "Work Task");
        urgentTaskPanel = createTaskPanel(urgentToDoList, "Urgent Task");

        // Tambahkan panel ke CardLayout
        mainPanel.add(personalTaskPanel, "Personal");
        mainPanel.add(workTaskPanel, "Work");
        mainPanel.add(urgentTaskPanel, "Urgent");

        // Tambahkan komponen ke frame
        add(taskTypeDropdown, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createTaskPanel(ToDoList toDoList, String taskType) {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
    
        JScrollPane scrollPane = new JScrollPane(taskPanel);
        panel.add(scrollPane, BorderLayout.CENTER);
    
        JPanel inputPanel = new JPanel(new GridLayout(1, 3));
        JTextField newTaskField = new JTextField();
        JButton addButton = new JButton("Add " + taskType);
        JButton deleteButton = new JButton("Delete Selected");
    
        inputPanel.add(newTaskField);
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);
        panel.add(inputPanel, BorderLayout.SOUTH);
    
        // Tambahkan logika untuk tombol Add
        addButton.addActionListener(e -> {
            String taskText = newTaskField.getText();
            if (!taskText.isEmpty()) {
                Task task;
                switch (taskType) {
                    case "Work Task":
                        task = new WorkTask(taskText);
                        break;
                    case "Urgent Task":
                        task = new UrgentTask(taskText);
                        break;
                    default:
                        task = new PersonalTask(taskText);
                        break;
                }
                toDoList.addTask(task);
                newTaskField.setText("");
                refreshTasks(taskPanel, toDoList);
            }
        });
    
        // Tambahkan logika untuk tombol Delete
        deleteButton.addActionListener(e -> {
            List<Task> tasks = toDoList.getTasks();
            List<Task> tasksToRemove = new ArrayList<>();
            for (Component component : taskPanel.getComponents()) {
                if (component instanceof JPanel) {
                    JPanel taskItemPanel = (JPanel) component;
                    for (Component subComponent : taskItemPanel.getComponents()) {
                        if (subComponent instanceof JCheckBox) {
                            JCheckBox checkBox = (JCheckBox) subComponent;
                            if (checkBox.isSelected()) {
                                // Hapus tugas yang terkait dengan checkbox
                                tasksToRemove.add((Task) checkBox.getClientProperty("task"));
                            }
                        }
                    }
                }
            }
            tasks.removeAll(tasksToRemove);
            refreshTasks(taskPanel, toDoList);
        });
    
        refreshTasks(taskPanel, toDoList);
        return panel;
    }

    private void refreshTasks(JPanel taskPanel, ToDoList toDoList) {
        taskPanel.removeAll();
        List<Task> tasks = toDoList.getTasks();
        for (Task task : tasks) {
            JPanel taskItemPanel = new JPanel(new BorderLayout());
            JLabel taskLabel = new JLabel(task.getDescription());
            JCheckBox checkBox = new JCheckBox();
    
            // Simpan referensi tugas di checkbox
            checkBox.putClientProperty("task", task);
    
            taskItemPanel.add(taskLabel, BorderLayout.CENTER);
            taskItemPanel.add(checkBox, BorderLayout.EAST);
    
            taskPanel.add(taskItemPanel);
        }
        taskPanel.revalidate();
        taskPanel.repaint();
    }
}