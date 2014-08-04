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
    private int _hpadding, _vpadding;

    public void setHorizontalPadding(int padding) {
        _hpadding = padding;
    }

    public void setVerticalPadding(int padding) {
        _vpadding = padding;
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
        int minContentSpaceRequired = 2 * _hpadding + SPACE_BTW_ICON_TXT + _icoWidth + _textWidth;
        int spaceLeft = _totalWidth - minContentSpaceRequired;

        if (_icoDirection == ICON_ALIGN_LEFT) {
            return _hpadding;
        }

        if (_icoDirection == ICON_ALIGN_RIGHT) {
            return _hpadding + spaceLeft + _textWidth + SPACE_BTW_ICON_TXT;
        }

        if (_icoDirection == ICON_ALIGN_RIGHT_OF_TEXT) {
            return (_totalWidth - _icoWidth + _textWidth + SPACE_BTW_ICON_TXT) / 2;
        }

        // if (_icoDirection == ICON_ALIGN_LEFT_OF_TEXT) {
        return (_totalWidth - _icoWidth - _textWidth - SPACE_BTW_ICON_TXT) / 2;
    }

    public int getLeftForText() {
        int minContentSpaceRequired = 2 * _hpadding + SPACE_BTW_ICON_TXT + _icoWidth + _textWidth;
        int spaceLeft = _totalWidth - minContentSpaceRequired;

        if (_icoDirection == ICON_ALIGN_RIGHT_OF_TEXT) {
            return (_totalWidth - _icoWidth - _textWidth - SPACE_BTW_ICON_TXT) / 2;
        }

        if (_icoDirection == ICON_ALIGN_LEFT) {
            return _hpadding + _icoWidth + SPACE_BTW_ICON_TXT + spaceLeft / 2;
        }

        if (_icoDirection == ICON_ALIGN_RIGHT) {
            return _hpadding + spaceLeft / 2;
        }

        // if (_icoDirection == ICON_ALIGN_LEFT_OF_TEXT) {
        return getRightForIcon() + SPACE_BTW_ICON_TXT;
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
