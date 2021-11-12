package io.monolabs.iamporttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.iamport.sdk.data.sdk.IamPortRequest
import com.iamport.sdk.data.sdk.PG
import com.iamport.sdk.data.sdk.PayMethod
import com.iamport.sdk.domain.core.Iamport
import io.monolabs.iamporttest.databinding.ActivityMainBinding
import java.util.*

class MainActivity:AppCompatActivity() {
  lateinit var binding: ActivityMainBinding
  override fun onCreate(savedInstanceState:Bundle?) {
    super.onCreate(savedInstanceState)
    ActivityMainBinding.inflate(layoutInflater).also {
      binding = it
      setContentView(binding.root)
      Iamport.init(this)
    }
  }

  override fun onStart() {
    super.onStart()

    val userCode = "iamport"  // 가맹점식별코드, "iamport" 는 테스트용 코드임
    // SDK 에 결제 요청할 데이터
    val request = IamPortRequest(
      pg = PG.kakaopay.makePgRawName(""),         // PG사
      pay_method = PayMethod.card.getPayMethodName(),                    // 결제수단
      name = "아임포트 진짜 쉬워요!",                      // 주문명
      merchant_uid = "sample_aos_${Date().time}",     // 주문번호
      amount = "1000",                                // 결제금액
      buyer_name = "김개발"
    )
    // 우측 하단 초록색 편지지 버튼 클릭
    binding.btn.setOnClickListener { view ->
      Snackbar.make(view, "아임포트에서 결제 해볼까요?", Snackbar.LENGTH_LONG)
        .setAction("결제") {
          // 아임포트에 결제 요청하기
          Iamport.payment("iamport", iamPortRequest = request, paymentResultCallback = {
            // 결제 완료 후 결과 콜백을 토스트 메시지로 보여줌
            Toast.makeText(this, "결제결과 => $it", Toast.LENGTH_LONG).show()
          })
        }.show()
    }
  }
}