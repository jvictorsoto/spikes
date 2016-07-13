package com.novoda.todoapp.navigation;

import android.app.Activity;
import android.content.Intent;

import com.novoda.todoapp.statistics.StatisticsActivity;
import com.novoda.todoapp.task.TaskDetailActivity;
import com.novoda.todoapp.task.data.model.Task;
import com.novoda.todoapp.task.edit.EditTaskActivity;
import com.novoda.todoapp.task.newtask.NewTaskActivity;
import com.novoda.todoapp.tasks.TasksActivity;

public class AndroidNavigator implements Navigator {

    public static final String EXTRA_TASK_ID = "EXTRA_TASK_ID"; //TODO handle this better

    private final Activity activity;

    public AndroidNavigator(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void toTaskDetail(Task task) {
        Intent intent = new Intent(activity, TaskDetailActivity.class);
        intent.putExtra(EXTRA_TASK_ID, task.id().value());
        activity.startActivity(intent);
    }

    @Override
    public void toEditTask(Task task) {
        Intent intent = new Intent(activity, EditTaskActivity.class);
        intent.putExtra(EXTRA_TASK_ID, task.id().value());
        activity.startActivity(intent);
    }

    @Override
    public void back() {
        activity.finish();
    }

    @Override
    public void toAddTask() {
        Intent intent = new Intent(activity, NewTaskActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void toStatistics() {
        fireTopLevelMenuIntent(StatisticsActivity.class);
    }

    @Override
    public void toTasksList() {
        fireTopLevelMenuIntent(TasksActivity.class);
    }

    private void fireTopLevelMenuIntent(Class<? extends Activity> activityClass) {
        Intent intent = new Intent(activity, activityClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
        activity.overridePendingTransition(0, 0);
    }
}