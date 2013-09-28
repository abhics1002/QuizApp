package com.abhi.gk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class DatabaseConnector {
	
	public static final String DATABASE_NAME = "QUIZ";
	public SQLiteDatabase database;
	public DatabaseOpenHelper databaseOpenHelper;	//database open helper 
	
	//constructor 
	
	public DatabaseConnector(Context context)
	{
		databaseOpenHelper = new DatabaseOpenHelper(context, DATABASE_NAME, null, 1);
	}
	
	// open a database connection 
	public void open() throws SQLException
	{
		Log.v("INFO", "DatabaseConnector-- open ---- START");
		database = databaseOpenHelper.getWritableDatabase();	
		Log.v("INFO", "DatabaseConnector-- open ---- END");
	}
	
	//close a database connection 
	public void close()
	{	
		Log.v("INFO", "DatabaseConnector-- close ---- START");
		if(database !=  null)
		{
			database.close();
		}
		
		Log.v("INFO", "DatabaseConnector-- close ---- END");
	}
	
	public Cursor getQuestions()
	{
		//open();
		Log.v("INFO", "DatabaseConnector-- getQuestions ---- START");
		return database.query("QUIZ",new String[] {"question", "a","b","c","d","answer"}, null, null, null, null, null);
		
		
	}
	
	public Cursor getQuestionsUsingPreferences(String question_id_prefernces)
	{	
		
		Log.v("DATABASECONNECTOR", "DatabaseConnector-- getQuestionsUsingPreferences ---- START");
		String temp= "SELECT DISTINCT * FROM QUIZ WHERE question_id NOT IN ("+question_id_prefernces+");";
		Log.v("getQuestionsUsingPreferences", temp);
		return database.rawQuery(temp, null);
				
	}
	public Cursor getOneQuestion()
	{	System.out.print("getOneQuestion----------START");
		open();
		// give question id randomly.
		int id = 2;
		return database.query(DATABASE_NAME, null,"question_id = "+ id, null, null, null, null);
	}
	
	public void insertQuestion(int question_id, String question, String a, String b, String c, String d, String answer, String category)
	{
		Log.v("INFO", "insertQuestion ---- START");
		ContentValues ques = new ContentValues();
		ques.put("question_id", question_id);
		ques.put("question", question);
		ques.put("a", a);
		ques.put("b", b);
		ques.put("c", c);
		ques.put("d", d);
		ques.put("answer", answer);
		ques.put("category", category);
		open();
		try{
			Log.v("INFO", "DB insert-- before inserting record");
			database.insert("QUIZ", null, ques);
			Log.v("INFO", "DB insert-- After inserting record");
		}
		catch(SQLException ex)
		{
			Log.v("INFO", "EXCEPTION --insert error in inserting record ");
		}
		
		close();
		Log.v("INFO", "insertQuestion ---- END");
	}
	
	public void deleteQuestion(int id)
	{
		open();
		database.delete(DATABASE_NAME, "id = "+ id , null);
		close();
	}
	
	public void getQuestionAnswer(int id)
	{
		open();
		database.query(DATABASE_NAME, null, "id ="+id, null, null, null, null);
		close();
	}
	
	public void populateDatabase()
	{
		Log.v("INFO", "DatabaseOpenhelper::populateDatabase ---- START");
		try{
	
		insertQuestion(1,"who is the president of india ?","DR Abdul Kalam", "Pranav Mukharji","Manmohansingh","Dr Sankar dayal sharma","b","P");
		insertQuestion(2,"Kiran Bedi received Magsaysay Award for government service in", "1992","1993","1994","1995","c","P");
		insertQuestion(3,"Logarithm tables were invented by","John Napier", "John Doe","John Harrison","John Douglas","a","S");
		insertQuestion(4,"With which sport is the Jules Rimet trophy associated?","Basketball", "Football","Hockey","Golf","b","S");
		insertQuestion(5,"Joule is the unit of","temperature", "pressure","energy","heat","c","S");
		insertQuestion(6,"Kemal Ataturk was","the first President of Independent Kenya", "the founder of modern Turkey","revolutionary leader of Soviet Union","None of the above","b","S");
		insertQuestion(7,"Milkha Singh Stood ____ in 1960 Olympics, in Athletics.","fourth in 400m final", "second in 400m final","eighth in 50km walk","seventh in 800m final","a","S");
		insertQuestion(8,"Ms. Medha Patkar is associated with the","Tehri project", "Enron project","Sardar Sarovar project","Dabhol project","c","P");
		insertQuestion(9,"Kathakali, Mohiniatam and Ottamthullal are the famous dances of","Kerala", "Karnataka","Orissa","Tamil Nadu","a","G");
		insertQuestion(10,"Jaspal Rana is associated with which of the following games?","Swimming", "Archery","Shooting","Weightlifting","c","S");
		insertQuestion(11,"Lala Lajpat Rai is also known as","Sher-e-Punjab", "Punjab Kesari","both (a) and (b)","None of the above","c","H");
		insertQuestion(12,"Modern football is said to have evolved from","England", "India","France","Spain","a","S");	
		insertQuestion(13,"The members of the Rajya Sabha are elected by","the people", "Lok Sabha","elected members of the legislative assembly","elected members of the legislative council","c","P");
		insertQuestion(14,"The power to decide an election petition is vested in the","Parliament", "Supreme Court","High courts","Election Commission","c","P");
		insertQuestion(15,"The present Lok Sabha is the","13th Lok Sabha", "14th Lok Sabha","15th Lok Sabha","16th Lok Sabha","c","P");
		insertQuestion(16,"The name of a candidate for the office of president of India may be proposed by","any five citizens of India", "any five members of the Parliament","any one member of the Electoral College","any ten members of the Electoral College","d","P");
		insertQuestion(17,"The famous Meenakshi temple is located in","Bihar", "Madurai","Madras","Trichy","b","G");
		insertQuestion(18,"'Tin Bhiga' lease by India to Bangladesh, was a part of","West Bengal", "Assam","Meghalaya","Tripura","a","G");
		insertQuestion(19,"National Science Centre is located at","Bangalore", "Bombay","Kolkata","Delhi","d","G");
		insertQuestion(20,"The world famous Ajanta caves are situated in","Orissa", "Andhra Pradesh","Kerala","Maharashtra","a","G");
		insertQuestion(21,"Former Australian captain Mark Taylor has had several nicknames over his playing career. Which of the following was NOT one of them?","Tubby", "Stodge","Helium Bat","Stumpy","d","G");
		insertQuestion(22,"Which was the 1st non Test playing country to beat India in an international match?","Canada", "Sri Lanka","Zimbabwe","East Africa","b","S");
		insertQuestion(23,"Who is the first Indian woman to win an Asian Games gold in 400m run?","M.L.Valsamma", "P.T.Usha","Kamaljit Sandhu","K.Malleshwari","c","S");
		insertQuestion(24,"Two of the great Mughals wrote their own memories. There were","Babar and Humayun", "Humayun and Jahangir","Babar and Jahangir","Jahangir and Shahjahan","c","H");
		insertQuestion(25,"To which king belongs the Lion capitol at Sarnath?","Chandragupta", "Ashoka","Kanishka","Harsha","b","H");
		insertQuestion(26,"The language of discourses of Gautama Buddha was","Bhojpuri", "Magadhi","Pali","Sanskrit","c","H");
		insertQuestion(27,"Under the Mountbatten Plan of 1947 the people of ___ were given the right to decide through a plebiscite whether they wished to join Pakistan or India.","Assam", "Punjab","Bengal","N.W.F.P and the Sylhet district of Assam","d","H");
		insertQuestion(28,"Which player has scored the most runs in a single Test innings?","Graham Gooch", "Matthew Hayden","Brian Lara","Agarkar","c","S");
		insertQuestion(29,"Who was the 1st ODI captain for India?","Ajit Wadekar", "Bishen Singh Bedi","Nawab Pataudi","Vinoo Mankad","a","S");
		insertQuestion(30,"The Asian Games were held in Delhi for the first time in...?","1951", "1962","1971","1982","a","S");
		insertQuestion(31,"The lead character in the film 'The Bandit Queen' has been played by","Rupa Ganguly", "Seema Biswas","Pratiba Sinha","Shabama Azmi","b","H");
		insertQuestion(32,"Who was the first captain of Indian Test team?","Vijay Hazare", "C K Nayudu","Lala Amarnath","Vijay Merchant","b","S");
		insertQuestion(33,"Who wrote the famous book - 'We the people'?","T.N.Kaul", "J.R.D. Tata","Khushwant Singh","Nani Palkhivala","d","B");
		insertQuestion(34,"Which of the following is NOT written by Munshi Premchand?","Gaban", "Godan","Guide","Manasorovar","c","A");
		insertQuestion(35,"Who has been awarded the first lifetime Achievement Award for his/her contribution in the field of Cinema?","Ashok Kumar", "Hou Hsio-hsein","Akiro Burosova","Bernardo Burtolucci","a","A");
		insertQuestion(36,"Gandhi Peace Prize for the year 2000 was awarded to the former President of South Africa along with","Sathish Dawan", "C. Subramanian","Grameen Bank of Bangladesh","World Healt Organisation","c","A");
		insertQuestion(37,"B. C. Roy Award is given in the field of","Music", "Journalism","Medicine","Environment","c","A");
		insertQuestion(38,"If an economy is equilibrium at the point where plans to save and to invest are equal, then government expenditure must be","zero", "equal to government income","larger than government income","negative","b","E");
		insertQuestion(39,"Which of the following is not an undertaking under the administrative control of Ministry of Railways?","Container Corporation of India Limited", "Konkan Railway Corporation Limited","Indian Railways Construction Company Limited","Diesel Locomotive Works, Varanasi","c","E");
		insertQuestion(40,"How many gold medals have been won by India in the Olympics so far?","4", "8","9","10","c","S");
		insertQuestion(41,"When was the first cricket Test match played?","1873", "1877","1870","1788","b","S");
		insertQuestion(42,"The first hand glider was designed by...?","Leonardo DaVinci", "The Wright brothers","Francis Rogallo","Galileo","a","G");
		insertQuestion(43,"In which Indian state did the game of Polo originate?","Meghalaya", "Rajasthan","Manipur","West Bengal","c","S");
		insertQuestion(44,"The central banking functions in India are performed by the (I): Central Bank of India   (II): Reserve Bank of India  (III): State Bank of India  (IV): Punjab National Bank","I II", "II","I","I III","b","E");
		insertQuestion(45,"Gilt-edged market means","England", "India","France","Spain","a","S");
		insertQuestion(46,"National expenditure includes","consumption expenditure", "investment expenditure","government expenditure","All of the above","d","E");
		insertQuestion(47,"The apex body for formulating plans and coordinating research work in agriculture and allied fields is","State Trading Corporation", "Regional Rural Banks","National Bank for Agriculture and Rural Development (NABARD)","Indian Council of Agricultural Research","d","E");
		insertQuestion(48,"Which of the following is not viewed as a national debt?","Provident Fund", "Life Insurance Policies","National Saving Certificate","Long-term Government Bonds","c","E");
		insertQuestion(49,"The condition of indirect taxes in the country's revenue is approximately","70 percent", "75 percent","80 percent","86 percent","d","E");
		insertQuestion(50,"Revenue of the state governments are raised from the following sources, except","entertainment tax", "expenditure tax","agricultural income tax","land revenue","c","E");
		}
		catch(SQLException ex)
		{
			Log.v("INFO", "DatabaseOpenhelper::populateDatabase -- ERROR while populating database----");
			System.out.print("ERROR while populating database");
		}
		Log.v("INFO", "DatabaseOpenhelper::populateDatabase ---- END");
	}
	
	private class DatabaseOpenHelper extends SQLiteOpenHelper 
	{
		
		public DatabaseOpenHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
			Log.v("INFO", "DatabaseConnector-- DatabaseOpenHelper CTOR---- START");
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db)
		{	
			Log.v("INFO", "DatabaseOpenhelper::OnCreate ---- START");
			String query = "CREATE TABLE QUIZ (question_id integer primary key , question TEXT, a TEXT, b TEXT,c TEXT, d TEXT, answer TEXT, category TEXT);";
			try{	
				Log.v("INFO", "DatabaseOpenhelper::OnCreate ---- before executing query");
				db.execSQL(query);
				Log.v("INFO", "DatabaseOpenhelper::OnCreate ---- AFTER executing query");
				//populateDatabase();
				//insertQuestion(1,"who is the president of india ?","DR Abdul Kalam", "Pranav Mukharji","Manmohansingh","Dr Sankar dayal sharma","b","P");
			}
			catch(SQLException ex)
			{
				System.out.print("SQL Exception generated");
				System.out.print("ERROR ---- while -----QUIZ table creating");
			}
			Log.v("INFO", "DatabaseConnector-- OnCreate ---- END");
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
	
		}
		
		
	}
}
