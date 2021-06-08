<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Classs extends Model
{
    use HasFactory;
    protected $fillable = [
        'ClassID',
        'ClassName',
        'Capacity',
        'StartTime',
        'EndTime',
        'IsDeleted'
    ];

    protected $table = 'Class';
}