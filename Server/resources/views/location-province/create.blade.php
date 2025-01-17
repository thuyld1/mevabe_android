@extends('layouts.backend')

@section('content')
    <div class="container">
        <div class="panel panel-default">
            <div class="panel-heading">Create New Province</div>
            <div class="panel-body">
                <a href="{{ url('/backend/setting-province') }}" title="Back">
                    <button class="btn btn-warning btn-xs"><i class="fa fa-arrow-left" aria-hidden="true"></i> Back
                    </button>
                </a>
                <br/>
                <br/>

                @if ($errors->any())
                    <ul class="alert alert-danger">
                        @foreach ($errors->all() as $error)
                            <li>{{ $error }}</li>
                        @endforeach
                    </ul>
                @endif

                {!! Form::open(['url' => '/backend/setting-province', 'class' => 'form-horizontal', 'files' => true]) !!}

                @include ('location-province.form')

                {!! Form::close() !!}

            </div>
        </div>
    </div>
@endsection
