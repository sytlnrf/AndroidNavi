package com.sytlnrf.shengyitao.andriodnavi;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sytlnrf.shengyitao.andriodnavi.model.Question;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MAIN";
    private final static String TAG_KEY_INDEX = "KEYINDEX";
    private final static String ANSWER_IS_TRUE_EXTRA = "com.sytlnrf.shengyitao.androidavi.answer_is_true";
    private final static String ANSWER_SHOW_EXTRA = "com.sytnrf.shengyitao.androidavi.answer_show";

    TextView mQuestionText;
    Button mtrueButon;
    Button mfalseButton;
    Button mnextButton;
    Button mcheatButton;
    private int currentQuestion = 0;
    private Question[] mQuestions = new Question[]{
            new Question(R.string.question1,true),
            new Question(R.string.question2,true),
            new Question(R.string.question3,false),
            new Question(R.string.question4,true),
            new Question(R.string.question5,false)
    };
    boolean mIsShowAnswer = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        if(savedInstanceState != null){
            currentQuestion = savedInstanceState.getInt(TAG_KEY_INDEX);
        }
        mQuestionText.setText(R.string.question1);

        mtrueButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
                checkTrueFalse(true);
            }
        });
        mfalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this,R.string.wrong_toast,Toast.LENGTH_SHORT).show();
                checkTrueFalse(false);
            }
        });
        mcheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean answerIsTrue = mQuestions[currentQuestion].ismTrueQuestion();
                Intent intent_cheat  = new Intent(MainActivity.this,CheatActivity.class);
                intent_cheat.putExtra(ANSWER_IS_TRUE_EXTRA, answerIsTrue);
//                startActivity(intent_cheat);
                startActivityForResult(intent_cheat, 0);
            }
        });
        mnextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentQuestion  = (currentQuestion + 1) % mQuestions.length;
                updateQuestion();
            }
        });
        updateQuestion();

    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if (data == null){
            return;
        }
        mIsShowAnswer = data.getBooleanExtra(ANSWER_SHOW_EXTRA,false);
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, TAG_KEY_INDEX);
        savedInstanceState.putInt(TAG_KEY_INDEX, currentQuestion);
    }
    private void checkTrueFalse(boolean userPressed){
        boolean questionTrue = mQuestions[currentQuestion].ismTrueQuestion();
        int messId;
        if (mIsShowAnswer){
            messId = R.string.judgement_toast;
        }else {
            if (userPressed == questionTrue) {
                messId = R.string.true_text;
            } else {
                messId = R.string.false_text;
            }
        }
        Toast.makeText(this,messId,Toast.LENGTH_SHORT).show();
    }
    public void updateQuestion(){
        int question = mQuestions[currentQuestion].getmQuestion();
        mQuestionText.setText(question);
    }


    private void initView(){
        mQuestionText = (TextView)findViewById(R.id.question_text);
        mtrueButon = (Button)findViewById(R.id.true_button);
        mfalseButton = (Button)findViewById(R.id.false_button);
        mnextButton = (Button)findViewById(R.id.next_button);
        mcheatButton = (Button)findViewById(R.id.cheat_button);

    }
//    @Override
//    public void onConfigurationChanged(Configuration configuration){
//        super.onConfigurationChanged(configuration);
//    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
