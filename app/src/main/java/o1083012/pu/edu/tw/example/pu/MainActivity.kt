package o1083012.pu.edu.tw.example.pu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , GestureDetector.OnGestureListener,
    GestureDetector.OnDoubleTapListener, View.OnTouchListener {

    lateinit var gDetector: GestureDetector
    var PictureNo: Int = 0  //目前顯示第幾張圖
    var TotalPictures: Int = 6 //總共幾張圖片(假設僅顯示pu0-pu3)
    var Flag: Boolean = true

    fun ShowPicture() {

        var res: Int = getResources().getIdentifier(
            "pu" + (PictureNo),
            "drawable", getPackageName()
        )
        img.setImageResource(res)

        txv.text = PictureNo.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gDetector = GestureDetector(this, this)
        img.setOnTouchListener(this)

        var res: Int = -1
        var countDrawables: Int = -1
        while (res != 0) {
            countDrawables++;
            res = getResources().getIdentifier(
                "pu" + (countDrawables),
                "drawable", getPackageName()
            );
        }
        TotalPictures = countDrawables

    }

  /*  override fun onTouchEvent(event: MotionEvent?): Boolean {
        gDetector.onTouchEvent(event)
        return true
    }
*/
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        gDetector.onTouchEvent(event)
        return true
    }

    override fun onDown(p0: MotionEvent?): Boolean {
        Flag=true
        return true
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
       return true
    }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
        PictureNo = 0
        ShowPicture()
        return true
    }

    override fun onLongPress(p0: MotionEvent?) {

    }


    override fun onShowPress(p0: MotionEvent?) {
    }

    override fun onScroll(
        e1: MotionEvent,
        e2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        if(Flag){

                if (e1.getY() < e2.getY()) {  //向右快滑
                    PictureNo++
                    if (PictureNo == TotalPictures) {
                        PictureNo = 0
                    }
                } else {     //向左快滑
                    PictureNo--;
                    if (PictureNo < 0) {
                        PictureNo = TotalPictures - 1
                    }
                }
                ShowPicture()

        }
        Flag=false
        return true
    }



    override fun onDoubleTap(p0: MotionEvent?): Boolean {
        PictureNo = TotalPictures - 1
        ShowPicture()
        return true
    }

    override fun onDoubleTapEvent(p0: MotionEvent?): Boolean {
        return true
    }

    override fun onSingleTapConfirmed(p0: MotionEvent?): Boolean {
        return true
    }


}
