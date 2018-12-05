.text
	la $a0, ar
	li $a1, 4
	jal PrintIntArray
	
	la $t0, ar
	addi $a1, $t0, 4
	addi $a2, $t0, 12
	#la $a0, ar
	#li $a1, 1
	#li $a2, 3
	jal Swap
	
	la $a0, ar
	li $a1, 4
	jal PrintIntArray
	
	li $v0, 10
	syscall
.data
	ar: .word 5
		.word 7
		.word 3
		.word 1
.text
#	a0 - array base
#	a1 - array size (jk, a1's actually a steak sauce)
PrintIntArray:
	addi $sp, $sp, -16
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	sw $s2, 12($sp)
	
	# $s0 - array base
	# $s1 - counter
	# $s2 - limit
	move $s0, $a0
	move $s2, $a1
	
	li $s1, 0
	startLoop:
		slt $t0, $s1, $s2
		beqz $t0, endLoop
		
		sll $t0, $s1, 2
		add $t0, $s0, $t0
		lw $a0, 0($t0)
		li $v0, 1
		syscall
		
		la $a0, comma
		li $v0, 4
		syscall
		
		addi $s1, $s1, 1
		b startLoop
	endLoop:
	
	Return:
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s1, 8($sp)
	lw $s2, 12($sp)
	addi $sp, $sp, 16
	jr $ra
	
.data
	comma: .asciiz ", "
	
.text
# a0 - array base
# a1 - *i * means pointer
# a2 - *j
Swap:
	addi $sp, $sp, -4
	sw $ra, ($sp) # can also be 0($sp)
	
	lw $t0, 0($a1)
	lw $t1, 0($a2)
	sw $t0, 0($a2)
	sw $t1, 0($a1)
	# old start
	#sll $t0, $a1, 2
	#add $t0, $a0, $t0
	#lw $t1, 0($t0)
	
	#sll $t2, $a2, 2
	#add $t2, $a0, $t2
	#lw $t3, 0($t2)
	
	#sw $t1, 0($t2)
	#sw $t3, 0($t0)
	# old end
	SwapReturn:
	lw $ra, ($sp)
	addi $sp, $sp, 4
	jr $ra

.data