<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Trainee extends Model
{
    use HasFactory;
    protected $fillable = [
        'UserName',
        'Email',
        'Phone',
        'Address',
        'Name',
        'IsActive',
        'Password',
        'ActivationCode',
        'ResetPasswordCode'
    ];

    protected $table = 'Trainee';
}
