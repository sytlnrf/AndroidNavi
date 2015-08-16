package com.sytlnrf.shengyitao.andriodnavi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private boolean mIsTrueAnswer;
    private final static String ANSWER_IS_TRUE_EXTRA = "com.sytlnrf.shengyitao.androidavi.answer_is_true";
    private final static String ANSWER_SHOW_EXTRA = "com.sytnrf.shengyitao.androidavi.answer_show";
    private TextView mAnswerTextView;
    private Button mShowAnswerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        initView();
        mIsTrueAnswer = getIntent().getBooleanExtra(ANSWER_IS_TRUE_EXTRA,false);
        setAnswerShowResult(false);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIsTrueAnswer){
                    mAnswerTextView.setText(R.string.true_text);
                }else {
                    mAnswerTextView.setText(R.string.false_text);
                }
                setAnswerShowResult(true);
            }
        });

    }
    private void setAnswerShowResult(boolean isAnswerShow){
        Intent intent = new Intent();
        intent.putExtra(ANSWER_SHOW_EXTRA,isAnswerShow);
        setResult(RESULT_OK,intent);
    }

    private void initView(){
        mAnswerTextView  = (TextView)findViewById(R.id.answer_textview);
        mShowAnswerButton = (Button)findViewById(R.id.show_answer_button);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cheat, menu);
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
