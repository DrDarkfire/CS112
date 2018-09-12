.text
  li $sv0, 4
  la $a0, output
  syscall
  
.data
output: .asciiz "Hello World\n"
