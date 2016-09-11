/*
Created by: Nick Basti & Gráinne Casey
11/8/15
 */
package com.example.nickdelta52.hackathonfinal;

import java.util.Random;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {

    //Tells game to run or not
    public static boolean Continue = false;

    //Default width of shape
    private final int SHAPE_HEIGHT = 150;
    //Default width of shape
    private final int SHAPE_WIDTH = 150;

    //Shape sizes
    private int shape1left;
    private int shape1top;
    private int shape2top;
    private int shape2left;
    private int shape3left;
    private int shape3top;
    private int specialShapeLeft;
    private int specialShapeTop;

    //Color line sizes
    private int colorLineLeft;
    private int colorLineTop;
    private int colorLineHeight = 150;

    //Width of player
    private final int PLAYER_WIDTH = 150;
    //Height of player
    private final int PLAYER_HEIGHT = 150;

    //Width of the screen
    private int screenWidth;
    //Height of the screen
    private int screenHeight;

    //Players X position
    private double playerX = 100;
    //Players Y position
    private int playerY = screenHeight - 200;

    //Our paint brushes
    private Paint paint1;
    private Paint paint2;
    private Paint paint3;
    private Paint paint4;
    private Paint paint5;
    private Paint paint6;
    private Paint paint7;

    //Random number
    private Random random;

    //Scores
    private int score = 0;
    private int highScore = 0;

    public GameView(Context context) {
        super(context);

        //Sets colors of all the paint brushes
        paint1 = new Paint();
        paint1.setColor(Color.parseColor("#FF0000")); //red

        paint2 = new Paint();
        paint2.setColor(Color.parseColor("#FFFF00")); //yellow

        paint3 = new Paint();
        paint3.setColor(Color.parseColor("#00B800")); //green

        paint4 = new Paint(); //Black

        paint5 = new Paint();
        paint5.setColor(Color.parseColor("#00B800")); //green

        paint6 = new Paint();
        paint6.setColor(Color.parseColor("#FF0066")); //pink

        paint7 = new Paint();
        paint7.setColor(Color.parseColor("#FF0000")); //red

        //Set the random number
        random = new Random(System.currentTimeMillis());
    }

    //Sets variables
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        //Screen dimensions
        screenHeight = h;
        screenWidth = w;

        //Shape Locations
        shape1left = screenWidth/2;
        shape1top = 10;
        shape2left = screenWidth/2 - 300;
        shape2top = 10 - (screenHeight/3);
        shape3left = screenWidth/2 + 100;
        shape3top = 10 - (2 * (screenHeight/3));

        //Special shape location
        specialShapeLeft = screenWidth/4 + 100;
        specialShapeTop = 10 - (screenHeight * 3);

        //player Y location
        playerY = screenHeight - 200;

        //Color line location
        colorLineLeft = 0;
        colorLineTop = 0 - (screenHeight * 3);
    }

    //This is the Game Loop that constantly runs
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw everything on the screen
        if (Continue) {
            drawPlayer(canvas);
            drawColorChangeLine(canvas);
            drawShapes(canvas);
            drawScore(canvas);
            drawHighScore(canvas);
        }

        //Collision detection
        if (((shape1top >= playerY && shape1top <= playerY + PLAYER_HEIGHT)
                || (shape1top + SHAPE_HEIGHT - 5 >= playerY && shape1top + SHAPE_HEIGHT - 5 <= playerY + PLAYER_HEIGHT))
                && ((shape1left >= playerX && shape1left <= playerX + PLAYER_WIDTH)
                || (shape1left + SHAPE_WIDTH >= playerX && shape1left + SHAPE_WIDTH <= playerX + PLAYER_WIDTH))) {
            if (paint1.getColor() == paint3.getColor()) {
                score += 1;
                if (score > highScore) {
                    highScore = score;
                }
            } else {
                showScoreDialog(score);
            }

        }

        //More Collision detection
        if (((shape2top >= playerY && shape2top <= playerY + PLAYER_HEIGHT)
                || (shape2top + SHAPE_HEIGHT - 5 >= playerY && shape2top + SHAPE_HEIGHT - 5 <= playerY + PLAYER_HEIGHT))
                && ((shape2left >= playerX && shape2left <= playerX + PLAYER_WIDTH)
                || (shape2left + SHAPE_WIDTH >= playerX && shape2left + SHAPE_WIDTH <= playerX + PLAYER_WIDTH))) {
            if (paint2.getColor() == paint3.getColor()) {
                score += 1;
                if (score > highScore) {
                    highScore = score;
                }

            } else {
                showScoreDialog(score);
            }
        }

        //Even More Collision detection
        if (((shape3top >= playerY && shape3top <= playerY + PLAYER_HEIGHT)
                || (shape3top + SHAPE_HEIGHT - 5 >= playerY && shape3top + SHAPE_HEIGHT - 5 <= playerY + PLAYER_HEIGHT))
                && ((shape3left >= playerX && shape3left <= playerX + PLAYER_WIDTH)
                || (shape3left + SHAPE_WIDTH >= playerX && shape3left + SHAPE_WIDTH <= playerX + PLAYER_WIDTH))) {
            if (paint5.getColor() == paint3.getColor()) {
                score += 1;
                if (score > highScore) {
                    highScore = score;
                }
            } else {
                showScoreDialog(score);
            }
        }

        //You get it
        if (((specialShapeTop >= playerY && specialShapeTop <= playerY + PLAYER_HEIGHT)
                || (specialShapeTop + SHAPE_HEIGHT - 5 >= playerY && specialShapeTop + SHAPE_HEIGHT - 5 <= playerY + PLAYER_HEIGHT))
                && ((specialShapeLeft >= playerX && specialShapeLeft <= playerX + PLAYER_WIDTH)
                || (specialShapeLeft + SHAPE_WIDTH >= playerX && specialShapeLeft + SHAPE_WIDTH <= playerX + PLAYER_WIDTH))) {

            score += 2;
            if (score > highScore) {
                highScore = score;
            }
        }

        //...
        if (((colorLineTop >= playerY && colorLineTop <= playerY + PLAYER_HEIGHT)
                || (colorLineTop + colorLineHeight - 5 >= playerY && colorLineTop + colorLineTop - 5 <= playerY + PLAYER_HEIGHT))) {
            int color = paint7.getColor();
            paint3.setColor(color);
        }

            invalidate();
    }

    //Sets all the random colors of our shapes
    private void setRandomColor(Paint paint) {
        int color = random.nextInt(10);
        if (color <= 1) {
            paint.setColor(Color.parseColor("#FF0000")); //red
        } else if (color >= 2 && color <= 4) {
            paint.setColor(Color.parseColor("#0000FF")); //blue
        } else if (color >=5 && color <= 7) {
            paint.setColor(Color.parseColor("#00B800")); //green
        } else if (color >=7) {
            paint.setColor(Color.parseColor("#FFFF00")); //yellow
        } else {
            paint.setColor(Color.parseColor("#0000FF")); //blue
        }
    }

    //Draws all our specific shapes
    private void drawShapes(Canvas canvas) {
        drawShape1(canvas);
        drawShape2(canvas);
        drawShape3(canvas);
        drawSpecialShape(canvas);
    }

    private void drawShape1(Canvas canvas) {
        int left = shape1left;
        int top = shape1top;
        int right = shape1left + SHAPE_WIDTH;
        int bottom = shape1top + SHAPE_HEIGHT;
        Rect rect = new Rect(left, top, right, bottom);
        canvas.drawRect(rect, paint1);

        shape1top +=10;

        if (top > screenHeight) {
            shape1top = 0 - SHAPE_HEIGHT - random.nextInt((750) + 1);
            shape1left = random.nextInt((screenWidth - SHAPE_WIDTH) - 10) + 10;
            setRandomColor(paint1);
        }
    }
    private void drawShape2(Canvas canvas) {
        int left = shape2left;
        int top = shape2top;
        int right = shape2left + SHAPE_WIDTH;
        int bottom = shape2top + SHAPE_HEIGHT;
        Rect rect = new Rect(left, top, right, bottom);
        canvas.drawRect(rect, paint2);

        shape2top +=10;

        if (top > screenHeight) {
            shape2top = 0 - SHAPE_HEIGHT - random.nextInt((750) + 1);
            shape2left = random.nextInt((screenWidth - SHAPE_WIDTH) - 10) + 10;
            setRandomColor(paint2);
        }
    }

    private void drawShape3(Canvas canvas) {
        int left = shape3left;
        int top = shape3top;
        int right = shape3left + SHAPE_WIDTH;
        int bottom = shape3top + SHAPE_HEIGHT;
        Rect rect = new Rect(left, top, right, bottom);
        canvas.drawRect(rect, paint5);

        shape3top +=10;

        if (top > screenHeight) {
            shape3top = 0 - SHAPE_HEIGHT - random.nextInt((750) + 1);
            shape3left = random.nextInt((screenWidth - SHAPE_WIDTH) - 10) + 10;
            setRandomColor(paint5);
        }
    }

    private void drawSpecialShape(Canvas canvas) { //shape = 1 or 2
        int left = specialShapeLeft;
        int top = specialShapeTop;
        int right = specialShapeLeft + SHAPE_WIDTH;
        int bottom = specialShapeTop + SHAPE_HEIGHT;
        Rect rect = new Rect(left, top, right, bottom);
        canvas.drawRect(rect, paint6);

        specialShapeTop +=10;

        if (top > screenHeight) {
            specialShapeTop = 0 - SHAPE_HEIGHT - (int) (Math.random() * 1000);
            specialShapeLeft = random.nextInt((screenWidth - SHAPE_WIDTH) - 10) + 10;
        }
    }


    private void drawColorChangeLine(Canvas canvas) {

        int left = colorLineLeft;
        int top = colorLineTop;
        int right = screenWidth;
        int bottom = colorLineTop + colorLineHeight;
        Rect rect = new Rect(left, top, right, bottom);
        canvas.drawRect(rect, paint7);

        colorLineTop += 10;

        if (colorLineTop > screenHeight) {
            colorLineTop = 0 - (screenHeight * 3);
            setRandomColor(paint7);
            while (paint7.getColor() == paint3.getColor()) {
                setRandomColor(paint7);
            }
        }
    }

    //Draws the player
    private void drawPlayer(Canvas canvas) {

        int top = playerY;
        int bottom = playerY + PLAYER_HEIGHT;
        double left = playerX;
        double right = playerX + PLAYER_WIDTH;
        Rect rect = new Rect((int) left, top, (int) right, bottom);
        canvas.drawRect(rect, paint3);
    }

    //Draws score
    private void drawScore(Canvas canvas) {

        int xPos = (canvas.getWidth() / 2) + 100;
        int yPos = (int) ((canvas.getHeight() / 2) - ((paint4.descent() + paint4.ascent()) / 2) - 800) ;

        paint4.setTextSize(75);

        canvas.drawText("Score: " + score, xPos, yPos, paint4);
    }

    //Draws high score
    private void drawHighScore(Canvas canvas) {

        int xPos = 10;
        int yPos = (int) ((canvas.getHeight() / 2) - ((paint4.descent() + paint4.ascent()) / 2) - 800) ;

        paint4.setTextSize(75);

        canvas.drawText("High Score: " + highScore, xPos, yPos, paint4);
    }

    //Resets all values back to default
    private void reset() {
        shape1left = screenWidth/2;
        shape1top = 10;
        shape2left = screenWidth/2;
        shape2top = 10 - (screenHeight/2);
        shape3left = screenWidth/2 + 100;
        shape3top = 10 - screenHeight;
        playerY = screenHeight - 200;
        colorLineLeft = 0;
        colorLineTop = 0 - (screenHeight * 3);
        score = 0;
    }

    //This code happens when the screen is touched
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        if (x > screenWidth - PLAYER_WIDTH)
        {
            x = screenWidth - PLAYER_WIDTH + 20;
        }

        while (playerX <= 20 + (int) x) {
            playerX +=.5;
        }
        while (playerX >= 20 + (int) x) {
            playerX -=.5;
        }

        return true;
    }

    //This Code resets game and creates the popup window when you lose
    private void showScoreDialog(int score) {
        reset();
        Continue = false;

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Your score was: " + score).setTitle("Game over!");
        builder.setPositiveButton("Play Again",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        Continue = true;
                    }
                });
        builder.show();
    }
}