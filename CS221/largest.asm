.text
main:
	li $a0, 6
	li $a1, 10
	li $a2, 2
	li $a3, 8
	jal findLargestAndAverage
	
	move $a0, $v0
	li $v0, 1
	syscall
	
	li $v0, 4
	la $a0, nl
	syscall
	
	move $a0, $v1
	li $v0, 1
	syscall
	
	li $v0, 4
	la $a0, nl
	syscall
	
.data
	nl: .asciiz "\n"

.text
findLargestAndAverage:
	#push stack, $ra, $a0, $a1, $a2, $a3
	addi $sp, $sp, -20
	sw $ra, 0($sp)
	sw $a0, 4($sp)
	sw $a1, 8($sp)
	sw $a2, 12($sp)
	sw $a3, 16($sp)
	sw $s0, 20($sp)
	
	jal findLargest
	move $s0, $v0
	move $a0, $s0
	move $a1, $a2
	jal findLargest
	move $s0, $v0
	move $a0, $s0
	move $a1, $a3
	jal findLargest
	move $s0, $v0 # the largest value is in $s0
	
	lw $t0, 4($sp)
	lw $t1, 8($sp)
	add $t0, $t0, $t1
	lw $t1, 12($sp)
	add $t0, $t0, $t1
	lw $t1, 16($sp)
	add $t0, $t0, $t1
	div $v1, $t0, 4
	
	# restore largest
	move $v0, $s0
	
	return:
		lw $ra, 0($sp)
		lw $s0, 20($sp)
		addi $sp, $sp 20
		jr $ra
		
findLargest:
	sgt $t0, $a0, $a1
	beqz $t0, alGreater
		move $v0, $a0
		b flReturn
	alGreater:
		move $v0, $a1
	flReturn:
		jr $ra
	
.data
