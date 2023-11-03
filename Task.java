package com.jokebox;
import java.awt.Toolkit;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Task {
	    static String activities [] = {"Wake up","Go to gym","Breakfast","Meetings","Lunch",
	            "Quick nap","Go to library","Dinner","Go to sleep"};

	    static String daysOfWeek [] = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        System.out.println("Select the day of the week \n0 - Sunday, \n1 - Monday,\n2 - Tuesday,\n3 - Wednesday,\n4 - Thursday,\n5 - Friday,\n6 - Saturday: ");
	        int day = scanner.nextInt();
	        System.out.println("Enter the hour (24-hour format): ");
	        int hour = scanner.nextInt();
	        System.out.println("Enter the minute: ");
	        int minute = scanner.nextInt();
	        System.out.println("Select the activity \n0 - Wake up, \n1 - Go to gym,\n2 - Breakfast,\n3 - Meetings,\n4 - Lunch,\n5 - Quick nap,\n6 - Go to library,\n7 - Dinner,\n8 - Go to sleep");
	        int activityIndex = scanner.nextInt();

	        String selectedDay = daysOfWeek[day];
	        String selectedActivity = activities[activityIndex];

	        scheduleReminder(selectedDay, hour, minute, selectedActivity);
	    }

	    public static void scheduleReminder(String day, int hour, int minute, String activity) {
	        Timer timer = new Timer();
	        TimerTask task = new TimerTask() {
	            public void run() {
	                System.out.println("It's time to " + activity + "!");
	                Toolkit.getDefaultToolkit().beep(); 
	                timer.cancel(); 
	            }
	        };

	        
	        long delay = calculateDelay(day, hour, minute);

	        
	        timer.schedule(task, delay);
	    }

	    public static long calculateDelay(String day, int hour, int minute) {
	        java.util.Calendar now = java.util.Calendar.getInstance();
	        int currentDay = now.get(java.util.Calendar.DAY_OF_WEEK) - 1; // Adjust for 0-based index

	        int daysUntilTarget = (dayToIndex(day) - currentDay + 7) % 7;

	        java.util.Calendar targetTime = (java.util.Calendar) now.clone();
	        targetTime.add(java.util.Calendar.DATE, daysUntilTarget);
	        targetTime.set(java.util.Calendar.HOUR_OF_DAY, hour);
	        targetTime.set(java.util.Calendar.MINUTE, minute);
	        targetTime.set(java.util.Calendar.SECOND, 0);

	        long delay = targetTime.getTimeInMillis() - now.getTimeInMillis();

	        if (delay < 0) {
	            
	            delay += 7 * 24 * 60 * 60 * 1000;
	        }

	        return delay;
	    }

	    public static int dayToIndex(String day) {
	        for (int i = 0; i < daysOfWeek.length; i++) {
	            if (daysOfWeek[i].equalsIgnoreCase(day)) {
	                return i;
	            }
	        }
	        return -1;
	    }
	}


