.text
la $a0, a
li $t2, 4
addi $t1, $t2, 0xc321 //overflows to negative
//addi answer, immediate1, immediate2
move $a0, $t1
li $v0, 1
syscall

.li $v0, 10
syscall
  
.data
.word 0x12345678
