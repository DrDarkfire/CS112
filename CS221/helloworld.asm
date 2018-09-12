.text
  li $sv0, 4 load immediate
  la $a0, output
  syscall
  
  li $v0, 1
  li $a0, 7
  syscall
  
  li $v0, 4
  la $a0, prompt
  syscall
  
  li $v0, 8
  la $a0, buffer
  li $a1, 80
  syscall
  
  li $v0, 4
  la $a0, newLine
  syscall
  
  li $v0, 4
  la $a0, buffer
  syscall
  
  li $v0, 10
  syscall
.data
output: .asciiz "Hello World\n"
prompt: .asciiz "Enter a string: "
buffer: .space 80
newLine: .asciiz "\n"
