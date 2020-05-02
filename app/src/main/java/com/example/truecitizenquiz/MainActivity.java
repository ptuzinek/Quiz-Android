package com.example.truecitizenquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button falseButton;
    private Button trueButton;
    private TextView questionTextView;
    private ImageButton nextButton;
    private int i = 0;
    private GifImageView gif;

    private Question[] questionBank = new Question[] {
            new Question(R.string.jordan_team, true),
            new Question(R.string.lebron_lost, true),
            new Question(R.string.championship_2019, false),
            new Question(R.string.most_championships, false),
            new Question(R.string.most_points, true),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Question question = new Question(R.string.question_declaration, true);

        falseButton = findViewById(R.id.false_button);
        trueButton = findViewById(R.id.true_button);
        questionTextView = findViewById(R.id.anwser_text_view);
        nextButton = findViewById(R.id.next_button);
        gif = findViewById(R.id.gif_image);

        falseButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.false_button:
                checkAnswer(false);

                break;
            case R.id.true_button:
                checkAnswer(true);

                break;
            case R.id.next_button:
                // go to the next question
                i= (i + 1) % questionBank.length;
                updateQuestion();
            }

        }

        private  void updateQuestion() {
            questionTextView.setText(questionBank[i].getAnswerResID());
        }
        private void checkAnswer(boolean userChoose) {
            boolean answerIsTrue = questionBank[i].isAnswerTrue();
            int toastMessageId = 0;

            if (userChoose == answerIsTrue) {
                toastMessageId = R.string.correct_answer;
                gif.setImageResource(R.drawable.jordandunkgif);
            }else {
                toastMessageId = R.string.wrong_answer;
                gif.setImageResource(R.drawable.dunkfaillonggif);
            }

            Toast.makeText(MainActivity.this, toastMessageId,
                    Toast.LENGTH_SHORT)
                    .show();
        }
}

