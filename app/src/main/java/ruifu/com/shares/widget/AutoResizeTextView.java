package ruifu.com.shares.widget;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class AutoResizeTextView extends TextView
{
  private Paint paint;
  private float b = 34.0F;
  private float c = 1.0F;
  private AutoResize audo;

  public AutoResizeTextView(Context paramContext)
  {
    super(paramContext);
    a(paramContext);
  }

  public AutoResizeTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    a(paramContext);
  }

  public AutoResizeTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    a(paramContext);
  }

  private void a(Context paramContext)
  {
    this.paint = new Paint();
    this.paint.set(getPaint());
    this.b = getTextSize();
    this.c = (int)(paramContext.getResources().getDisplayMetrics().scaledDensity * 8.0F + 0.5F);
  }

  private void a(String paramString, int paramInt)
  {
    int i;
    int j;
    int k;
    float f1;
    if (paramInt > 0)
    {
      paramInt = paramInt - getPaddingLeft() - getPaddingRight();
      i = getHeight();
      j = getPaddingBottom();
      k = getPaddingTop();
      f1 = this.b;
      this.paint.setTextSize(f1);
    }
    while (true)
    {
      float f2;
      if (this.paint.measureText(paramString) <= paramInt)
      {
//        f2 = f1;
//        if (this.paint.descent() - this.paint.ascent() <= i - j - k);
      }
      else
      {
//        f1 -= 1.0F;
//        if (f1 > this.c)
          break;
//        f2 = this.c;
      }
//      setTextSize(0, f2);
//      if ((f2 == this.c) && (this.paint.measureText(paramString) > paramInt) && (this.audo != null))
        this.audo.a(this);
      return;
//      this.paint.setTextSize(f1);
    }
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (paramInt1 != paramInt3)
      a(getText().toString(), paramInt1);
  }

  protected void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    a(paramCharSequence.toString(), getWidth());
  }

  public void setOnTextViewResizeListener(AutoResize paramq)
  {
    this.audo = paramq;
  }

  public void setTextSize(float paramFloat)
  {
    super.setTextSize(paramFloat);
    this.b = getTextSize();
  }
}

