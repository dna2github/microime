package org.sevenlab.microime

import android.content.Context
import android.inputmethodservice.InputMethodService
import android.view.View
import android.view.inputmethod.ExtractedTextRequest
import android.view.inputmethod.InputConnection
import android.view.inputmethod.InputMethodManager
import android.widget.Button

class MicroIME : InputMethodService() {
    private class KeyClickListener: View.OnClickListener {
        val m: MicroIME;
        var code: Int = 0;

        constructor(m_: MicroIME, c_: Int) {
            m = m_
            code = c_
        }
        override fun onClick(p0: View?) {
            val ic = m.getIC() ?: return;
            // ic.getTextBeforeCursor() getTextAfterCursor() deleteSurroundingText()
            // ic.setComposingText()
            val et = ic.getExtractedText(ExtractedTextRequest(), 0)
            val cur: Int = et.selectionStart
            ic.commitText("" + code, 0);
            ic.setSelection(cur, cur)
        }
    }
    private var ic: InputConnection? = null;

    fun getIC(): InputConnection? { return ic; }

    override fun onCreateInputView(): View {
        val kv: View = layoutInflater.inflate(R.layout.custom_layout, null);
        (kv.findViewById(R.id.sw) as Button).setOnClickListener(View.OnClickListener {
            // val intent = Intent(Settings.ACTION_INPUT_METHOD_SETTINGS)
            // startActivity(intent)
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager;
            imm?.showInputMethodPicker()
        })
        (kv.findViewById(R.id.b0) as Button).setOnClickListener(KeyClickListener(this, 0))
        (kv.findViewById(R.id.b1) as Button).setOnClickListener(KeyClickListener(this, 1))
        (kv.findViewById(R.id.b2) as Button).setOnClickListener(KeyClickListener(this, 2))
        (kv.findViewById(R.id.b3) as Button).setOnClickListener(KeyClickListener(this, 3))
        (kv.findViewById(R.id.b4) as Button).setOnClickListener(KeyClickListener(this, 4))
        (kv.findViewById(R.id.b5) as Button).setOnClickListener(KeyClickListener(this, 5))
        (kv.findViewById(R.id.b6) as Button).setOnClickListener(KeyClickListener(this, 6))
        (kv.findViewById(R.id.b7) as Button).setOnClickListener(KeyClickListener(this, 7))
        (kv.findViewById(R.id.b8) as Button).setOnClickListener(KeyClickListener(this, 8))
        (kv.findViewById(R.id.b9) as Button).setOnClickListener(KeyClickListener(this, 9))

        return kv;
    }

    /*override fun onStartInputView(editorInfo: EditorInfo, restarting: Boolean) {
        // editorInfo.inputType and InputType.TYPE_MASK_CLASS
        // TYPE_CLASS_NUMBER, TYPE_CLASS_DATETIME, TYPE_CLASS_PHONE, TYPE_CLASS_TEXT
        // TYPE_TEXT_VARIATION_PASSWORD, TYPE_TEXT_VARIATION_URI, TYPE_TEXT_FLAG_AUTO_COMPLETE
    }*/
    /*
    onCreateCandidatesView()
    */

    override fun onWindowShown() {
        super.onWindowShown();
        ic = currentInputConnection;
    }

    override fun onWindowHidden() {
        super.onWindowHidden()
        ic = null;
    }
}