package application;
	


import java.util.GregorianCalendar;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class calendar extends Application {
	Button forward = new Button(">>");// global component of calendar
	Button backward = new Button("<<");
	GridPane gp = new GridPane();
	Label date = new Label();
	GregorianCalendar calendar = new GregorianCalendar();

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		String[] monthes = { "jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec" };

		date = new Label(monthes[calendar.get(calendar.MONTH)] + " " + (String.valueOf(calendar.get(calendar.YEAR))));// label
																														// to
																														// display
																														// the
																														// month
																														// and
																														// year
		HBox hb1 = new HBox();// HBox to add first raw of buttons
		hb1.getChildren().addAll(backward, date, forward);
		hb1.setSpacing(127);
		HBox hb2 = new HBox();// onother HBox to days Labels
		hb2.getChildren().addAll(new Label("Sun"), new Label("Mon"), new Label("Tue"), new Label("Wed"),
				new Label("Thu"), new Label("Fri"), new Label("Sat"));
		hb2.setSpacing(38);
		int currentDay = calendar.get(calendar.DATE);// Determine the current Day
		calendar.set(calendar.DAY_OF_MONTH, 1);
		int numOfDayInMonth = calendar.getActualMaximum(calendar.DATE);
		int dayInMonth = calendar.get(calendar.DATE);
		int day = calendar.get(calendar.DAY_OF_WEEK);
		int c = 0;// number of coulmns in GridPane
		int r = 0;// number of raws in GridPane

		for (int i = 1; i < day; i++) { // ignore days unitl the first day in the month
			gp.add(new Label(" _ "), c, 0);
			c++;
		}
		day--;
		do { // print the month days

			if (day % 7 != 0) {
				if (currentDay == calendar.get(calendar.DATE)) { // highlight the current day
					Label l = new Label(String.valueOf(calendar.get(calendar.DATE)));
					l.setTextFill(Color.GOLD);
					gp.add(l, c, r);
				} else {
					gp.add(new Label(String.valueOf(calendar.get(calendar.DATE))), c, r);
				}
			} else {
				if (currentDay == calendar.get(calendar.DATE)) { // highlight the current day
					Label l = new Label(String.valueOf(calendar.get(calendar.DATE)));
					l.setTextFill(Color.GOLD);
					gp.add(l, c, r);
				} else {
					c = 0;
					r++;
					gp.add(new Label(String.valueOf(calendar.get(calendar.DATE))), c, r);
				}
			}
			c++;
			day++;
			calendar.add(calendar.DATE, 1);
			dayInMonth++;
		} while (dayInMonth <= numOfDayInMonth);

		forward.setOnAction(new EventHandler<ActionEvent>() {// handle forword button

			@Override
			public void handle(ActionEvent e) {
				gp.getChildren().clear();
				int newYear = calendar.get(calendar.YEAR);
				int newMonth = calendar.get(calendar.MONTH);
				if (calendar.get(calendar.MONTH) == 12) {
					newYear++;
					newMonth++;
				} else {
					newMonth++;
				}
				calendar = new GregorianCalendar(newYear, newMonth - 1, 1);// update the next month information
				date.setText(
						monthes[calendar.get(calendar.MONTH)] + " " + (String.valueOf(calendar.get(calendar.YEAR))));

				calendar.set(calendar.DAY_OF_MONTH, 1);
				int numOfDayInMonth = calendar.getActualMaximum(calendar.DATE);
				int dayInMonth = calendar.get(calendar.DATE);
				int day = calendar.get(calendar.DAY_OF_WEEK);
				int c = 0;
				int r = 0;
				for (int i = 1; i < day; i++) {
					gp.add(new Label(" _ "), c, 0);// ignore days unitl the first day in the month
					c++;
				}
				day--;
				do {// print the month days

					if (day % 7 != 0) {
						gp.add(new Label(String.valueOf(calendar.get(calendar.DATE))), c, r);
					} else {
						c = 0;
						r++;
						gp.add(new Label(String.valueOf(calendar.get(calendar.DATE))), c, r);

					}
					c++;
					day++;
					calendar.add(calendar.DATE, 1);
					dayInMonth++;
				} while (dayInMonth <= numOfDayInMonth);

			}
		});

		backward.setOnAction(new EventHandler<ActionEvent>() {// handle backward button

			@Override
			public void handle(ActionEvent e) {
				gp.getChildren().clear();
				int newYear = calendar.get(calendar.YEAR);
				int newMonth = calendar.get(calendar.MONTH);
				if (calendar.get(calendar.MONTH) == 12) {
					newYear--;
					newMonth--;
				} else {
					newMonth--;
				}
				calendar = new GregorianCalendar(newYear, newMonth - 1, 1);// update the previous month information
				date.setText(
						monthes[calendar.get(calendar.MONTH)] + " " + (String.valueOf(calendar.get(calendar.YEAR))));

				calendar.set(calendar.DAY_OF_MONTH, 1);
				int numOfDayInMonth = calendar.getActualMaximum(calendar.DATE);
				int dayInMonth = calendar.get(calendar.DATE);
				int day = calendar.get(calendar.DAY_OF_WEEK);
				int c = 0;
				int r = 0;
				for (int i = 1; i < day; i++) {
					gp.add(new Label(" _ "), c, 0);
					c++;
				}
				day--;
				do {

					if (day % 7 != 0) {
						gp.add(new Label(String.valueOf(calendar.get(calendar.DATE))), c, r);
					} else {
						c = 0;
						r++;
						gp.add(new Label(String.valueOf(calendar.get(calendar.DATE))), c, r);

					}
					c++;
					day++;
					calendar.add(calendar.DATE, 1);
					dayInMonth++;
				} while (dayInMonth <= numOfDayInMonth);

			}
		});

		gp.setHgap(46);
		VBox vb = new VBox();// VBox to add two Hbox and GridPane
		vb.getChildren().addAll(hb1, hb2, gp);
		Scene s = new Scene(vb, 400, 200);
		primaryStage.setTitle("4th assignment: My Calenadar");
		primaryStage.setScene(s);
		primaryStage.show();

	}
}
