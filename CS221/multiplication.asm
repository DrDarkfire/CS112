.text
  li $t0, 10
  li $t1, 20
  mult $t0, $t1
  mflo $t3        move from low
  li $v0, 10
  syscall
  
  li $t0, 19
  li $t1, 4
  div $t1, $t0
  
  mflo $t3 would be 4
  mfhi $t3 would be 3
  
  syscall
