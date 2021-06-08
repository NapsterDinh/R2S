<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Answer extends Model
{
    use HasFactory;
    protected $fillable = [
        'ClassID',
        'ModuleID',
        'TraineeID',
        'QuestionID',
        'value'
    ];

    protected $table = 'Answer';
}
