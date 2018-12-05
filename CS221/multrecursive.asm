.text
	#prompt for m
	li $v0, 4
	la $a0, prompt1
	syscall
	li $v0, 5
	syscall
	move $s0, $v0

	#prompt for n
	li $v0, 4
	la $a0, prompt2
	syscall
	li $v0, 5
	syscall
	move $s1, $v0
	
	#Mult(m,n)
	move $a0, $s0
	move $a1, $s1
	jal Mult
	move $s2, $v0
	
	li $v0, 4
	la $a0, result
	syscall
	li $v0, 1
	move $a0, $s2
	syscall
	
	li $v0, 10
	syscall
	
.text
# Subprogram Mult
# Do a recursive Multiple
# Reg conventions
# $a0 - m
# $a1 - n
# returns $v0, m*n
Mult:
	addi $sp, $sp, -8
	sw $a0, 4($sp)
	sw $ra, 0($sp)
	
	seq $t0, $a1, 1
	beqz $t0, Recurse
		lw $v0, 4($sp)
		b Return
	Recurse:
	#mult (m, n - 1)
	subi $a1, $a1, 1
	jal Mult
	lw $t0, 4($sp)
	add $v0, $t0, $v0
	
	Return:
	lw $ra, 0($sp)
	addi $sp, $sp, 8
	jr $ra
	
.data
	prompt1: .asciiz "\nEnter multiplicand: "
	prompt2: .asciiz "\nEnter multiplier: "
	result: .asciiz "\nnm*n = "