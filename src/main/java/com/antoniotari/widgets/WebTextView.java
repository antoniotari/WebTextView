package com.antoniotari.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

/**
 * Created by Antonio Tari on 08/09/14.
 */
public class WebTextView extends LinearLayout {

    protected View mView;
    protected WebView mWebView;
    String mFontFamily;
    int mFontColor=-1;
    String mFont;
    String mText;
    String mSecondaryFont;

    private static final String DEFAULT_FONT = "Muli";
    private static final String DEFAULT_FONT_FAMILY = "'Muli', sans-serif";
    private static final int DEFAULT_FONT_COLOR = Color.BLACK;

    public WebTextView(Context context){
        this(context, null);
    }

    public WebTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.webtextview, this,true);
        mWebView =(WebView) mView.findViewById(R.id.textwebview);
        mWebView.setBackgroundColor(Color.TRANSPARENT);

        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.WebTextView);
        for (int i = 0; i < a.getIndexCount(); ++i){
            int attr = a.getIndex(i);
            if(attr==R.styleable.WebTextView_font) {
                mFont = a.getString(attr);
            } else if(attr==R.styleable.WebTextView_fontColor) {
                mFontColor = a.getInt(attr, Color.BLACK);
            } else if(attr==R.styleable.WebTextView_fontFamily) {
                mFontFamily = a.getString(attr);
            } else if(attr==R.styleable.WebTextView_secondaryFont) {
                mSecondaryFont = a.getString(attr);
            } else if(attr==R.styleable.WebTextView_text) {
                mText = a.getString(attr);
            }
        }
        a.recycle();

        if(mText!=null){
            setText(mText);
        }
    }

    /**
     * go on https://www.google.com/fonts to pick a font
     * example of a valid font : "Tangerine"
     * serFont("Tangerine")
     */
    public void setFont(final String font) {
        mFont = font;
    }

    public String getFont() {
        if(mFont==null)return DEFAULT_FONT;
        return mFont;
    }

    public void setFontColor(final int fontColor) {
        mFontColor = fontColor;
    }

    public int getFontColor() {
        if(mFontColor<0)return DEFAULT_FONT_COLOR;
        return mFontColor;
    }

    /**
     * go on https://www.google.com/fonts to pick a font
     * example of a valid font family : "'Tangerine', cursive"
     * serFontFamily("'Tangerine', cursive")
     * if not set font will be used as font family
     */
    public void setFontFamily(final String fontFamily) {
        mFontFamily = fontFamily;
    }

    public String getFontFamily() {
        if(mFontFamily==null && mFont==null)return DEFAULT_FONT_FAMILY;
        else if(mFontFamily==null)return "'"+mFont+"'";
        return mFontFamily;
    }


    public void setText(String text){
        setText(text,getFontColor(),getFont(),getFontFamily());
    }

    public void setText(final String text, int color,final String googleFontName,final String googleFontFamily,String dfd) {
    }

    public void setText(final String text, int color,final String googleFontName,final String googleFontFamily) {
        String textColor ="#" + Integer.toHexString(color).substring(2); // "#ffffff";

        StringBuilder stringBuilder=new StringBuilder("<html>");
        stringBuilder.append("<head>");
        stringBuilder.append("<link href='http://fonts.googleapis.com/css?family="+googleFontName + "'  rel='stylesheet' type='text/css'>");
        //stringBuilder.append("<link href='http://fonts.googleapis.com/css?family=Dawning+of+a+New+Day' rel='stylesheet' type='text/css'>");
        stringBuilder.append("<style type=\"text/css\">");
        stringBuilder.append("body{");
        stringBuilder.append("color: "+textColor+";");
        stringBuilder.append("font-family: "+googleFontFamily+";");
        //stringBuilder.append("mFont-family: 'Dawning of a New Day', cursive;");
        stringBuilder.append("}");
        stringBuilder.append("</style>");
        stringBuilder.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\" />");
        stringBuilder.append("</head>");
        stringBuilder.append("<body>");
        stringBuilder.append(text);
        stringBuilder.append("</body>");
        stringBuilder.append("</html>");

        Log.d("blublu",stringBuilder.toString());

        mWebView.loadData(stringBuilder.toString(), "text/html; charset=UTF-8", null);

        WebSettings settings= mWebView.getSettings();
        //settings.setDefaultFontSize(12);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }
}
