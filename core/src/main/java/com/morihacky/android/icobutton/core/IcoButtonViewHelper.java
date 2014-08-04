package com.morihacky.android.icobutton.core;

public class IcoButtonViewHelper {

    public static int ICON_ALIGN_LEFT_OF_TEXT = 0;
    public static int ICON_ALIGN_RIGHT_OF_TEXT = 1;
    public static int ICON_ALIGN_LEFT = 2;
    public static int ICON_ALIGN_RIGHT = 3;
    public static int SPACE_BTW_ICON_TXT = 10;


    private int _totalWidth;
    private int _totalHeight;
    private int _icoWidth;
    private int _textWidth;
    private int _textHeight;
    private int _icoDirection;
    private int _padding;

    public void setPadding(int padding) {
        _padding = padding;
    }

    public void setIcoAlign(int icoDirection) {
        _icoDirection = icoDirection;
    }

    public void setTotalWidth(int totalWidth) {
        _totalWidth = totalWidth;
    }

    public void setTotalHeight(int totalHeight) {
        _totalHeight = totalHeight;
    }

    public void setIcoWidth(int icoWidth) {
        _icoWidth = icoWidth;
    }

    public void setTextWidth(int textWidth) {
        _textWidth = textWidth;
    }

    public void setTextHeight(int textHeight) {
        _textHeight = textHeight;
    }


    public int getRightForIcon() {
        return getLeftForIcon() + _icoWidth;
    }

    public int getLeftForIcon() {
        int minContentSpaceRequired = 2 * _padding + SPACE_BTW_ICON_TXT + _icoWidth + _textWidth;
        int spaceLeft = _totalWidth - minContentSpaceRequired;

        if (_icoDirection == ICON_ALIGN_LEFT) {
            return _padding;
        }

        if (_icoDirection == ICON_ALIGN_RIGHT) {
            return _padding + spaceLeft + _textWidth + SPACE_BTW_ICON_TXT;
        }

        if (_icoDirection == ICON_ALIGN_RIGHT_OF_TEXT) {
            return (_totalWidth - _icoWidth + _textWidth + SPACE_BTW_ICON_TXT) / 2;
        }

        // if (_icoDirection == ICON_ALIGN_LEFT_OF_TEXT) {
        return (_totalWidth - _icoWidth - _textWidth - SPACE_BTW_ICON_TXT) / 2;
    }

    public int getLeftForText() {
        int minContentSpaceRequired = 2 * _padding + SPACE_BTW_ICON_TXT + _icoWidth + _textWidth;
        int spaceLeft = _totalWidth - minContentSpaceRequired;

        if (_icoDirection == ICON_ALIGN_RIGHT_OF_TEXT) {
            return (_totalWidth - _icoWidth - _textWidth - SPACE_BTW_ICON_TXT) / 2;
        }

        if (_icoDirection == ICON_ALIGN_LEFT) {
            return _padding + _icoWidth + SPACE_BTW_ICON_TXT +  spaceLeft/2;
        }

        if (_icoDirection == ICON_ALIGN_RIGHT) {
            return _padding + spaceLeft / 2;
        }

        // if (_icoDirection == ICON_ALIGN_LEFT_OF_TEXT) {
        return getRightForIcon() + _padding;
    }


    public int getRightForText() {
        return getLeftForText() + _textWidth;
    }

    public int getTopForText() {
        return (_totalHeight - _textHeight) / 2;
    }

    public int getBottomForText() {
        return getTopForText() + _textHeight;
    }

    public int getTopForIcon() {
        return getTopForText();
    }

    public int getBottomForIcon() {
        return getBottomForText();
    }
}
