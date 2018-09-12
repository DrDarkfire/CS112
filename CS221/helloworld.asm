.text
  li $sv0, 4
  la $a0, output
  syscall
  
  li $v0, 1
  li $a0, 7
  syscall
  
  li $v0, 10
  syscall
.data
output: .asciiz "Hello World\n"
