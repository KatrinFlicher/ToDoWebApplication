package by.gsu.epamlab.factories;

import by.gsu.epamlab.beans.Task;
import by.gsu.epamlab.impl.TaskDBImpl;
import by.gsu.epamlab.interfaces.ITaskDAO;

import java.util.ArrayList;
import java.util.List;

public class TaskFactory {
    public static ITaskDAO getTaskDAO(){
        return new TaskDBImpl();
    }
}
